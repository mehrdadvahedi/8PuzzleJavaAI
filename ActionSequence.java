class ActionSequence {
    int size;
    Action header, trailer;

    // Constructor
    ActionSequence() {
        size = 0;
        header = new Action(null, null, null);
        trailer = new Action(null, header, null);
        header.next = trailer;
    }

    // Add an element to the last of the action sequence.
    void addLast(Action v) {
        v.next = trailer;
        v.prev = trailer.prev;
        trailer.prev.next = v;
        trailer.prev = v;
        size++;
    }

    // Print all elements of the action sequence.
    void printList() {
        Action current = trailer.prev;
        while (current != header) {
            System.out.print("\t" + current.element);
            current = current.prev;
        }
        System.out.println();
    }

    void printActionSequence(Problem problem) {
        int[][] state = P.copy(problem.initial_state);
        Action current = trailer.prev;
        P.puzzle(state);
        while (current != header) {
            state = P.copy(problem.Successor(state, current.element));
            P.println();
            P.println();
            P.println("\t" + current.element);
            P.puzzle(state);
            P.println();
            current = current.prev;
        }
        System.out.println();
    }

}
