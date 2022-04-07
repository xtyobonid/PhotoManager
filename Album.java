public class Album {
	private String name;
	private String condition;
	private String[] conditions;
	private PhotoManager manager;
	int tagComparisons;
	
	// Constructor
	public Album(String _name, String _condition, PhotoManager _manager) {
		name = _name;
		condition = _condition;
		conditions = new String[1];
		if (_condition.contains(" AND ")) {
			conditions = _condition.split(" AND ");
		} else {
			conditions[0] = _condition;
		}
		manager = _manager;
		tagComparisons = -1;
	}
	
	// Return the name of the album
	public String getName() {
		return name;
	}
	
	// Return the condition associated with the album
	public String getCondition() {
		return condition;
	}
	
	// Return the manager
	public PhotoManager getManager() {
		return manager;
	}
	
	// Return all photos that satisfy the album condition
	public LinkedList<Photo> getPhotos() {
		tagComparisons = 0;
		if (condition.equals("")) {
			return manager.getAllPhotos();
		}
		
		LinkedList<Photo> toReturn = new LinkedList<Photo>();
		BST<LinkedList<Photo>> tree = manager.getPhotos();
		for (int i = 0; i < conditions.length; i++) {
			tagComparisons += tree.countFindKeyComparisons(conditions[i]);
			if (tree.findKey(conditions[i])) {
				LinkedList<Photo> concat = tree.retrieve();
				concat.findFirst();
				while(!concat.last()) {
					LinkedList<String> photoTags = concat.retrieve().getTags();
					boolean check = true;
					for (int j = 0 ; j < conditions.length; j++) {
						check = check && (photoTags.find(conditions[j]));
					}
					if (check) {
						toReturn.insert(concat.retrieve());
					}
					concat.findNext();
				}
				
				LinkedList<String> photoTags = concat.retrieve().getTags();
				boolean check = true;
				for (int j = 0 ; j < conditions.length; j++) {
					check = check && (photoTags.find(conditions[j]));
				}
				if (check) {
					toReturn.insert(concat.retrieve());
				}
			}
		}
		 
		return toReturn;
	}
	
	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps() {
		return tagComparisons;
	}
}
