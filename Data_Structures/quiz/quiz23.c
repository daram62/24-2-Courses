listPointer invertedCopyList(listPointer ptr) {
    listPointer lead = ptr, middle = NULL, trail;

    while (lead) {
        listPointer newNode = (listPointer)malloc(sizeof(*newNode));
        newNode->data = lead->data;

        newNode -> link = middle;
        middle = newNode;

        lead = lead -> link;
    }

    return middle;
}