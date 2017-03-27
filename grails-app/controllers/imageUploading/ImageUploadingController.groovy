package imageUploading

import grails.converters.JSON
import grails.transaction.Transactional

class ImageUploadingController {

	def transloaditService

    def index() {
		try {
			def signatureMap = transloaditService.createSignature(grailsApplication.config.transloadit.templates.imagesUpload)
			render(view: "/index", model: [signatureMap: signatureMap])
		} catch (Exception e) {
			log.error("Failed to create Transloadit signature.", e)
			flash.error = "Failed to create Transloadit signature"
			render(view: "/index")
		}
    }

	def uploadImages() {
		String assembly = params.assembly as String
		List images = transloaditService.getImageURLsFromTransloadit(assembly, 'optimized')
		render(images as JSON)
	}

	@Transactional
	def save() {
		ImageHolder imageHolderInstance = new ImageHolder(params)
		try {
			if (!imageHolderInstance.save()) {
				log.warn("Failed to save image holder $imageHolderInstance.id\n$imageHolderInstance.errors.allErrors")
				flash.error = "Failed to save image holder instance."
				forward(action: 'index')
			} else {
				flash.message = "Success!"
				forward(action: 'index')
			}
		} catch (Exception e) {
			log.warn("Failed to create image holder from params $params", e)
			flash.error = "Failed to create image holder from params $params"
			forward(action: 'index')
		}
	}
}
