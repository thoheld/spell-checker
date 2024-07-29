// BinarySearchTree.java

public class BinarySearchTree<Type extends Comparable<Type>> {
    // a class for one node of the tree
    private class Node {
        public Type data;
        public Node left;
        public Node right;

        // initialize the node with the given value
        public Node(Type data) {
            this.data = data;
            left = null;
            right = null;
        }
    };

    // reference to the root node of the tree
    public Node root;
    
    // set the whole tree to null
    public BinarySearchTree() {
        root = null;
    }

    // perform inorder traversal on the tree
    private void inorderPrint(Node node) {
        if (node != null) {
            inorderPrint(node.left);
            System.out.print(node.data + " ");
            inorderPrint(node.right);
        }
    }
    
    // print the entire tree
    public void print() {
        inorderPrint(root);
        System.out.println();
    }

    // recursive function to insert at a particular node
    private Node insertAt(Type value, Node node) {
        if (node == null) {
            return new Node(value);
        }

        if (node.data.compareTo(value) < 0) {
            // right
            node.right = insertAt(value, node.right);
        } else {
            // left
            node.left = insertAt(value, node.left);
        }

        return node;
    }
    
    // insert a number calls recursive function to insert at the root
    public void insert(Type value) {
        root = insertAt(value, root);
    }
    
    // perform the recursive search at a given node
    private Node searchAt(Type target, Node node) {
        if (node == null) {
            return null;
        } else if (node.data.equals(target)) {
            return node;
        } else if (node.data.compareTo(target) < 0) {
            return searchAt(target, node.right);
        } else {
            return searchAt(target, node.left); 
        } 
    }

    // search for a value and return it, or null if not found
    public Type search(Type target) {
        Node node = searchAt(target, root);
        if (node == null) {
            return null;
        } else {
            return node.data;
        } 
    }

    // find the smallest node value in a subtree
    private Node min(Node node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    // recursively search for a value and remove it when found
    private Node removeAt(Type value, Node node) {
        if (node == null) {
            return null;
        } else if (node.data.compareTo(value) > 0) {
            node.left = removeAt(value, node.left);
        } else if (node.data.compareTo(value) < 0) {
            node.right = removeAt(value, node.right);
        } else {
            // zero children, return null
            if (node.left == null && node.right == null) {
                return null;
            }
            // one child, return that one child
            else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // two children, swap nodes, and recurse
            else {
                // find smallest node in right subtree
                Node swap = min(node.right);

                // put node's data into this one
                node.data = swap.data;

                // delete that node instead
                node.right = removeAt(swap.data, node.right);
            }
        }
        
        return node;
    }

    // remove a given value from the tree
    public void remove(Type value) {
        root = removeAt(value, root);
    }
    
    
    
    // Thomas's Project 3 Work
    
    /*
     * This method returns the height of the tree.
     */
    public int height() {
    	return heightHelper(root); 
    }
    /*
     * This is a helper function for height(). It traverses the tree recursively and
     * calculates the longest path from root to leaf (which is the height).
     */
    public int heightHelper(Node r) {
    	// base case
    	if (r == null) { return 0; }
    	
    	// recursive case
    	int leftHeight = heightHelper(r.left); // height of left subtree
    	int rightHeight = heightHelper(r.right); // height of right subtree
    	// return the greater of the two heights + self
    	if (leftHeight > rightHeight) { return leftHeight + 1; }
    	return rightHeight + 1;
    }
    
    
    /*
     * This method takes a string and searches the dictionary for a word that the given
     * string is a prefix of. If a word is found, it is returned. Otherwise returns NULL.
     */
    public String prefix(String str) {
    	return prefixHelper(root, str);
    }
    /*
     * This is a helper function for prefix(). It searches the tree recursively, looking for
     * a matching word. If no matching word is found, it returns NULL.
     */
    public String prefixHelper(Node r, String str) {
    	// base case
    	if (r == null) { return null; } // no word found
    	
    	// recursive case
    	String word = (String) r.data; // current word
    	
    	if (str.compareTo(word) >= 0) { // if str is >= word, go to right child
    		return prefixHelper(r.right, str);
    		
    	} else if (str.length() <= word.length()) { // check that word is long enough to contain str
    		if (str.compareTo(word.substring(0, str.length())) == 0) { // if word contains prefix
    			return word;
    		}
    	}
    	
    	return prefixHelper(r.left, str); // go to left child
    	
    }
    
    
    
    // Thomas's Lab 6 Work
    
    /*
     * This method returns the number of nodes in the tree. It calls countHelper()
     * to perform the recursive counting operation.
     */
    public int count() {
    	return countHelper(root);
    }
    /*
     * This methed returns the number number of nodes the given tree using recursion.
     */
    private int countHelper(Node r) {
    	// base case
    	if (r == null) {
    		return 0;
    	}
    	// recursive case - count left subtree, right subtree, and self
    	return countHelper(r.left) + countHelper(r.right) + 1;
    }
}
