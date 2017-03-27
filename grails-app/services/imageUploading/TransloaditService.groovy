package imageUploading

import imageUploading.exceptions.TransloaditException
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.jackson.map.ObjectMapper
import org.joda.time.DateTime

/**
 * Service for working with transloadit responses
 */
@Transactional(readOnly = true)
class TransloaditService {

    def grailsApplication

    /**
     * Creates transloadit signature string.
     *
     * @return signature string
     */
    def createSignature(String templateId) {
        if (templateId) {
            String apiKey = grailsApplication.config.transloadit.api.key
            String apiSecret = grailsApplication.config.transloadit.api.secret
            def expires = DateTime.now().plusHours(2).toDate().format("YYYY/MM/dd HH:mm:ss+00:00", TimeZone.getTimeZone('UTC'))
            def data = ["auth": ["key": "${apiKey}", "expires": "${expires}"], "template_id": "${templateId}"] as JSON
            String signature = GeneralUtils.hmac(data.toString(), apiSecret)
            [params: data.toString(), signature: signature]
        }
    }

    /**
     * Parses Transloadit response JSON string.
     * @param transloadit JSON string received from Transloadit.
     * @return JSONObject representation of parsed Transloadit response JSON string.
     * @throws TransloaditException
     */
    private parseResponse(String transloadit) throws TransloaditException {
        def json = new ObjectMapper().readValue(transloadit, Map.class)
        if (!json.ok) {
            throw new TransloaditException("Transloadit returned response with failure result.")
        }

        json
    }

    /**
     * Get URLs of uploaded images from Transloadit response.
     *
     * @param assembly params map received from Transloadit
     * @param name of the step from Transloadit template.
     * @throws TransloaditException if Transloadit response is incorrect, or hasn't been able to process request
     */
    List getImageURLsFromTransloadit(String assembly, String name) throws TransloaditException {
        def parsedResponse = parseResponse(assembly)
        def result
        try {
            result = parsedResponse.results."$name".collect {[field:it.field, url: it.url]}
        } catch (Exception e) {
            throw new TransloaditException("Couldn't parse Transloadit response", e)
        }

        result
    }
}



