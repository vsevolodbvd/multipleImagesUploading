package imageUploading

class ImageHolder implements Serializable {

    String firstImage
    String secondImage

    static constraints = {
        firstImage nullable: true, blank: true
        secondImage nullable: true, blank: true
    }
}