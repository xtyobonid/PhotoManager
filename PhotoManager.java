public class PhotoManager {
	private LinkedList<Photo> allPhotos;
	private BST<LinkedList<Photo>> invertedIndexTree;
	
	// Constructor
	public PhotoManager() {
		allPhotos = new LinkedList<Photo>();
		invertedIndexTree = new BST<LinkedList<Photo>>();
	}
	
	// Add a photo
	public void addPhoto(Photo p) {
		if (allPhotos.find(p)) {
			return;
		}
		
		allPhotos.insert(p);
		
		LinkedList<String> tags = p.getTags();
		if (tags.empty()) {
			return;
		}
		tags.findFirst();
		while (!tags.last()) {
			if (!invertedIndexTree.findKey(tags.retrieve())) {
				LinkedList<Photo> toInsert = new LinkedList<Photo>();
				toInsert.insert(p);
				invertedIndexTree.insert(tags.retrieve(), toInsert);
			} else {
				LinkedList<Photo> tag = invertedIndexTree.retrieve();
				tag.insert(p);
			}
			
			tags.findNext();
		}
	}
	
	// Delete a photo
	public void deletePhoto(String path) {
		LinkedList<String> tags;
		if (allPhotos.find(new Photo(path, new LinkedList<String>()))) {
			tags = allPhotos.retrieve().getTags();
			allPhotos.remove();
			
			tags.findFirst();
			while (!tags.last()) {
				invertedIndexTree.findKey(tags.retrieve());
				LinkedList<Photo> tag = invertedIndexTree.retrieve();
				if (tag.find(new Photo(path, new LinkedList<String>()))) {
					tag.remove();
				}
				
				if (tag.empty()) {
					invertedIndexTree.removeKey(invertedIndexTree.retrieveKey());
				}
				
				tags.findNext();
			}
		}
	} 
	
	// Return the inverted index of all managed photos
	public BST<LinkedList<Photo>> getPhotos() {
		return invertedIndexTree;
	}
	
	public LinkedList<Photo> getAllPhotos() {
		return allPhotos;
	}
}
