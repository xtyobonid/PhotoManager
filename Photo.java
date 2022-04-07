public class Photo {
	private String path;
	private LinkedList<String> tags;
	// Constructor
	public Photo(String _path, LinkedList<String> _tags) {
		path = _path;
		tags = _tags;
	}
	// Return the path (full file name) of the photo. A photo is uniquely identified by its path.
	public String getPath() {
		return path;
	}
	// Return all tags associated with the photo
	public LinkedList<String> getTags() {
		return tags;
	} 
	
	public boolean equals(Object equalTo) {
		Photo photo = (Photo) equalTo;
		return (path.equals(photo.path));
	}
}
