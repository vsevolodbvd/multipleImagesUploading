package imageUploading

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.security.SignatureException

class GeneralUtils {

	/**
	 * Calculates hash-based message authentication code (HMAC).
	 *
	 * @param data data
	 * @param key  key
	 * @return HMAC
	 * @throws SignatureException
	 */
	static String hmac(String data, String key) throws SignatureException {
		def result

		try {
			// get an hmac_sha1 key from the raw key bytes
			def signingKey = new SecretKeySpec(key.bytes, 'HmacSHA1')

			// get an hmac_sha1 Mac instance and initialize with the signing key
			def mac = Mac.getInstance('HmacSHA1')
			mac.init(signingKey)

			// compute the hmac on input data bytes
			def rawHmac = mac.doFinal(data.bytes)
			result = rawHmac.encodeHex()
		} catch (Exception e) {
			throw new SignatureException('Failed to generate HMAC: ' + e.message)
		}

		result
	}
}
