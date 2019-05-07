import java.util.Queue;
import java.util.LinkedList;
import java.lang.StringBuilder;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) { val = x; }
}

public class Serialization {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder("[");
    if (root == null) {
      sb.append(']');
      return sb.toString();
    }
    // We are adding null's to the queue to hold the positions
    // Therefore we cannot use ArrayDeque
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    // Use a boolean flag to tell us if the nodes in a level are all nulls
    boolean allNulls = false;
    while (!queue.isEmpty()) {
      // Break immediately if all nodes in this level are null
      if (allNulls) {
        break;
      }
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        if (curr == null) {
          sb.append("null,");
          continue;
        }
        sb.append(curr.val);
        sb.append(',');
        queue.offer(curr.left);
        queue.offer(curr.right);
        // Update the flag
        if (i == 0) {
          allNulls = curr.left == null && curr.right == null;
        } else {
          allNulls = curr.left == null && curr.right == null && allNulls;
        }
      }
    }
    // Post-proccessing: remove the extra ',' at the end and add a final ']'
    sb.deleteCharAt(sb.length() - 1);
    sb.append(']');
    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data == null || data.length() == 0) {
      return null;
    }
    // Parse the input string to an array of string
    String[] keys = data.replaceAll("\\[|\\]", "").split(",");
    // Validate the input to not be anything that looks like "" or "[]"
    if (keys.length == 0 || (keys.length == 1 && keys[0].length() == 0)) {
      return null;
    }
    int keySize = keys.length;
    // Start from the first node 
    // index represents the current key in the input that we are trying to build a node with
    int index = 0;
    TreeNode root = buildNode(keys, index++);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        // Build the corresponding left and right children of this node
        TreeNode left = index >= keySize ? null : buildNode(keys, index++);
        TreeNode right = index >= keySize ? null : buildNode(keys, index++);
        curr.left = left;
        curr.right = right;
        // Add the non-null children to the queue
        if (left != null) {
          queue.offer(left);
        }
        if (right != null) {
          queue.offer(right);
        }
        // Stop if we have built all nodes
        if (index >= keySize) {
          break;
        }
      }
    }
    return root;
  }

  private TreeNode buildNode(String[] keys, int index) {
    if (keys[index].equals("null")) {
      return null;
    }
    return new TreeNode(Integer.parseInt(keys[index]));
  }

  public static void main(String[] args) {
    Serialization Serialization = new Serialization();
    TreeNode root = Serialization.deserialize("[1,2,3,null,null,4,5]");
    System.out.println(Serialization.serialize(root));

    root = Serialization.deserialize("[]");
    System.out.println(Serialization.serialize(root));

    root = Serialization.deserialize("");
    System.out.println(Serialization.serialize(root));

    root = Serialization.deserialize("[3,2,4,1]");
    System.out.println(Serialization.serialize(root));
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
