package com.example.educationapplication.model;

import java.util.ArrayList;

import com.example.educationapplication.search.dataObjects.CourseDto;

public class CourseAVL {
    public CourseDto course;
    public CourseAVL leftNode;
    public CourseAVL rightNode;

    public CourseAVL(CourseDto currCourse, CourseAVL left, CourseAVL right) {
        this.course = currCourse;
        this.leftNode = left;
        this.rightNode = right;
    }

    //Null AVLTree for the course
    public CourseAVL() {
        this.course = null;
    }

    /**
     * Inserts a new course into the AVL tree.
     *
     * @param currCourse the course to insert
     * @return the root of the updated AVL tree
     */
    public CourseAVL insert(CourseDto currCourse) {
        // If the course to insert is null, return the current node
        if (currCourse == null) {
            return this;
        }

        // If the current node is null, set it to the course to insert and return it
        if (this.course == null) {
            this.course = currCourse;
            return this;
        }

        // If the course to insert has a greater ID than the current node's course ID, insert it to the right
        if (currCourse.getCourseId() > this.course.getCourseId()) {
            CourseAVL insertRight;
            // If the right node is null, create a new node and set it to the right of the current node
            if (this.rightNode == null) {
                this.rightNode = new CourseAVL();
            }
            // Create a new node with the current node as the left child and the right node's subtree with the course to insert inserted
            insertRight = new CourseAVL(this.course, this.leftNode, this.rightNode.insert(currCourse));
            // Check if a right rotation is needed to balance the tree
            if (insertRight.getBalanceFactor() > 1) {
                // Check if a left rotation is needed before performing the right rotation
                if ((insertRight.leftNode != null) && (insertRight.leftNode.getBalanceFactor() < 0)) {
                    insertRight.leftNode = insertRight.leftNode.leftRotate();
                }
                // Perform a right rotation
                insertRight = insertRight.rightRotate();
                return insertRight;
            }
            // Check if a left rotation is needed to balance the tree
            if (insertRight.getBalanceFactor() < -1) {
                // Check if a right rotation is needed before performing the left rotation
                if (!(insertRight.rightNode == null) && (insertRight.rightNode.getBalanceFactor() > 0)) {
                    insertRight.rightNode = insertRight.rightNode.rightRotate();
                }
                // Perform a left rotation
                insertRight = insertRight.leftRotate();
                return insertRight;
            }
            return insertRight;
        }
        // If the course to insert has a smaller or equal ID to the current node's course ID, insert it to the left
        else {
            // If the left node is null, create a new node and set it to the left of the current node
            if (this.leftNode == null) {
                this.leftNode = new CourseAVL();
            }
            // Create a new node with the current node as the right child and the left node's subtree with the course to insert inserted
            CourseAVL insertLeft = new CourseAVL(this.course, this.leftNode.insert(currCourse), this.rightNode);
            // Check if a right rotation is needed to balance the tree
            if (insertLeft.getBalanceFactor() > 1) {
                // Check if a left rotation is needed before performing the right rotation
                if (!(insertLeft.leftNode == null) && (insertLeft.leftNode.getBalanceFactor() < 0)) {
                    insertLeft.leftNode = insertLeft.leftNode.leftRotate();
                }
                // Perform a right rotation
                insertLeft = insertLeft.rightRotate();
                return insertLeft;
            }
            // Check if a left rotation is needed to balance the tree
            if (insertLeft.getBalanceFactor() < -1) {
                // Check if a right rotation is needed before performing the left rotation
                if (!(insertLeft.rightNode == null) && (insertLeft.rightNode.getBalanceFactor() > 0)) {
                    insertLeft.rightNode = insertLeft.rightNode.rightRotate();
                }
                //Perform a left rotation
                insertLeft = insertLeft.leftRotate();
                return insertLeft;
            }
            return insertLeft;
        }
    }

    // rightRotate method for performing right rotations on AVL tree
    public CourseAVL rightRotate() {
        if (this.leftNode == null || this.leftNode.course == null) {
            return this;
        }
        CourseAVL newParent = this.leftNode;
        CourseAVL newLeftOfCurrent = newParent.rightNode;
        CourseAVL curr = this;
        curr.leftNode = newLeftOfCurrent;
        newParent.rightNode = curr;
        return newParent;
    }

    // leftRotate method for performing left rotations on AVL tree
    public CourseAVL leftRotate() {
        if (this.rightNode == null || this.rightNode.course == null) return this;
        CourseAVL newParent = this.rightNode;
        CourseAVL newRightOfCurrent = newParent.leftNode;
        CourseAVL curr = this;
        curr.rightNode = newRightOfCurrent;
        newParent.leftNode = curr;
        return newParent;
    }

    /**
     * Returns the height of the tree rooted at this node.
     * The height of a node is the maximum number of edges
     * from the node to any leaf node.
     *
     * @return the height of the tree rooted at this node
     */
    public int getHeight() {
        int leftNodeHeight;
        if (this.leftNode == null) {
            leftNodeHeight = 0;
        }
        else {
            leftNodeHeight = 1 + this.leftNode.getHeight();
        }

        int rightNodeHeight;
        if (this.rightNode == null) {
            rightNodeHeight = 0;
        }
        else {
            rightNodeHeight = 1 + this.rightNode.getHeight();
        }

        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    /**
     * Returns the balance factor of the tree rooted at this node.
     * The balance factor of a node is the difference between the
     * height of its left subtree and the height of its right subtree.
     *
     * @return the balance factor of the tree rooted at this node
     */
    public int getBalanceFactor() {
        int leftHeight;
        int rightHeight;
        if (leftNode == null) {
            leftHeight = 0;
        }
        else {
            leftHeight = leftNode.getHeight();
        }
        if (rightNode == null) {
            rightHeight = 0;
        }
        else {
            rightHeight = rightNode.getHeight();
        }

        return leftHeight - rightHeight;
    }

    /**
     * Performs an in-order traversal of the tree rooted at this node.
     * An in-order traversal visits the left subtree, then the root,
     * then the right subtree.
     *
     * @return an ArrayList of CourseDto objects visited in order
     */
    public ArrayList<CourseDto> inOrderTraversal() {
        ArrayList<CourseDto> out = new ArrayList<>();
        inOrderTraversalHelper(out);
        return out;
    }

    /**
     * Helper method for inOrderTraversal().
     * Performs an in-order traversal of the tree rooted at this node.
     * An in-order traversal visits the left subtree, then the root,
     * then the right subtree.
     *
     * @param out the ArrayList to which CourseDto objects should be added
     */
    private void inOrderTraversalHelper(ArrayList<CourseDto> out) {
        if (this.leftNode != null) {
            this.leftNode.inOrderTraversalHelper(out);
        }
        out.add(this.course);
        if (this.rightNode != null) {
            this.rightNode.inOrderTraversalHelper(out);
        }
    }

}
