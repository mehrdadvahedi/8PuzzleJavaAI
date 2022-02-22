class Action {
    String element;
    Action next, prev;

    // Constructor:
    // Create a node with the given element and next node.
    Action(String element, Action prev, Action next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }

}
