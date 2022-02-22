class Queue {
    int size;
    QNode header, trailer;

    // Constructor
    Queue() {
        size = 0;
        header = new QNode(null, null, null);
        trailer = new QNode(null, header, null);
        header.next = trailer;
    }

    // Add an element to the first of the queue
    void addFirst(QNode v) {
        v.next = header.next;
        v.prev = header;
        header.next.prev = v;
        header.next = v;
        size++;
    }

    // Add an element to the last of the queue.
    void addLast(QNode v) {
        v.next = trailer;
        v.prev = trailer.prev;
        trailer.prev.next = v;
        trailer.prev = v;
        size++;
    }

    // Add an element v after a given element u
    void addAfter(QNode u, QNode v) {
        v.prev = u;
        v.next = u.next;
        u.next.prev = v;
        u.next = v;
        size++;
    }

    void addSortedByG(QNode v) {
        QNode current = header.next;
        while (current != trailer) {
            if (current.element.path_cost >= v.element.path_cost) {
                // add v before current
                current.prev.next = v;
                v.prev = current.prev;
                current.prev = v;
                v.next = current;
                this.size++;
                return;
            }
            current = current.next;
        }
        this.addLast(v);
    }

    void addSortedByH(QNode v) {
        QNode current = header.next;
        while (current != trailer) {
            if (Huristic.h(current) >= Huristic.h(v)) {
                // add v before current
                current.prev.next = v;
                v.prev = current.prev;
                current.prev = v;
                v.next = current;
                this.size++;
                return;
            }
            current = current.next;
        }
        this.addLast(v);
    }

    void addSortedByF(QNode v) {
        QNode current = header.next;
        while (current != trailer) {
            if (Huristic.f(current) >= Huristic.f(v)) {
                // add v before current
                current.prev.next = v;
                v.prev = current.prev;
                current.prev = v;
                v.next = current;
                this.size++;
                return;
            }
            current = current.next;
        }
        this.addLast(v);
    }

    // Remove an element from head (first of the queue)
    QNode removeFirst() {
        // print an error if the queue is empty.
        if (header.next == trailer) {
            System.out.println("The queue is empty. ");
            return null;
        }

        QNode t;
        t = header.next;
        header.next = t.next;
        t.next.prev = header;
        t.next = null;
        t.prev = null;
        size--;
        return t;
    }

    // Print all elements of the queue
    void printQueue() {
        QNode current = header.next;
        while (current != trailer) {
            System.out.print("\n" + current.element.path_cost);
            current = current.next;
        }
        System.out.println();
    }

}
