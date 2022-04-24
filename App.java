public class App{
    public static void main(String args[]){
        LinkedList linkedList              =    new LinkedList();
        LinkedList linkedListStack         =    new LinkedList();
        LinkedList linkedListQueue         =    new LinkedList();
        Muppet animal                      =    new Muppet("Animal", 9);
        Muppet beaker                      =    new Muppet("Beaker", 4);
        Muppet gonzo                       =    new Muppet("Gonzo", 21);
        Muppet kermit                      =    new Muppet("Kermit", 1);
        Muppet missPiggy                   =    new Muppet("Miss Piggy", 16);
        Muppet swedishChef                 =    new Muppet("Swedish Chef", 7);

        
        linkedList.print();
        linkedList.push(animal, 1);
        linkedList.push(beaker, 2);
        linkedList.push(gonzo, 3);
        linkedList.push(kermit, 4);
        linkedList.push(missPiggy, 5);
        linkedList.push(swedishChef, 6);
        linkedList.print();
        linkedList.push(linkedList.pop("Swedish Chef"),1);
        linkedList.push(linkedList.pop("Pig"), 1);

        linkedList.print();

        
        Stack stack = new Stack(linkedListStack);
        stack.pop();
        stack.print();
        System.out.println("Push Kermit, Beaker en Chef op Stack:");
        stack.push(linkedList.pop("Kermit"));
        stack.push(linkedList.pop("Beaker"));
        stack.push(linkedList.pop("Swedish Chef"));

        System.out.println("Linked List:");
        linkedList.print();
        System.out.println("Stack:");
        stack.print();
        
        Queue queue = new Queue(linkedListQueue);
        queue.pop();
        queue.print();
        System.out.println("Push Gonzo, Piggy en Animal in Queue:");
        queue.push(linkedList.pop("Gonzo"));
        queue.push(linkedList.pop("Miss Piggy"));
        queue.push(linkedList.pop("Animal"));

        System.out.println("Linked List:");
        linkedList.print();
        System.out.println("Queue:");
        queue.print();

        System.out.println("Push alles in Queue naar Linked List");
        int times = queue.size();
        for(int i = 0; i < times; i++){
            linkedList.push(queue.pop(), 1);
        }

        System.out.println("Queue:");
        queue.print();

        System.out.println("Push alles in Stack naar Linked List");
        times = stack.size();
        for(int i = 0; i < times; i++){
            linkedList.push(stack.pop(), 1);
        }

        System.out.println("Stack:");
        stack.print();

        System.out.println("Linked List:");
        linkedList.print();
    }
}

class Muppet{
    private String naam;
    private int leeftijd;
    public Muppet next;

    public Muppet(String naam, int leeftijd){
        setNaam(naam);
        setLeeftijd(leeftijd);
    }

    //This methods prints the name and age of the muppet
    public void print(){
        System.out.println("--------------");
        System.out.println("Muppet: " + naam);
        System.out.println("Leeftijd: " + leeftijd);
    }

    public String getNaam(){
        return naam;
    }

    public void setNaam(String nieuweNaam){
        this.naam = nieuweNaam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }
}

class LinkedList{
    private int size = 0;        
    Muppet muppetStart;
    private Muppet currentMuppet;
    private Muppet tempMuppet;
    private int count;

    /* Method print()
    This method prints the contents of the list in the terminal. First we check if the list is empty, if not, we go through the list and print the names
    of every muppet we encounter.
    */
    public void print(){
        if(size() == 0){
            System.out.println("De lijst is leeg");
        } else {
            System.out.println("Grootte van de lijst: " + size);
            currentMuppet = muppetStart;
            for(int i = 0; i < size; i++){        
                System.out.println(currentMuppet.getNaam());
                currentMuppet = currentMuppet.next;
            }
        }       
    }

    /* Method push(Muppet m, int p)
    This methods checks where the user wants to put the muppet. There are three possiblities, in front, in between or behind the other muppets.
    Based on this information field 'next' in a muppet object is changed to place the muppet on place 'p'. This method is made fool proof.
    */
    void push(Muppet m, int p){
        if(p == 1){ //If user pushes at the first place
            if(muppetStart == null){ //If list is empty
                muppetStart = m;
            } else { //Replace first muppet with 'm'
                currentMuppet = muppetStart;
                muppetStart = m;
                m.next = currentMuppet;
            } 
            size++;           
        } else if ((p > 0) && (p < size)){//If user pushes somewhere in the middle
            currentMuppet = muppetStart;
            for(int i = 1; i < p; i++){
                currentMuppet = currentMuppet.next;
            }
            m.next = currentMuppet.next;
            currentMuppet = muppetStart;
            for(int i = 1; i <(p-1); i++){
                currentMuppet = currentMuppet.next;
            }
            currentMuppet.next = m;
            size++;
        } else if (size < p){//If user pushes at the end
            currentMuppet = muppetStart;
            for(int i = 1; i < (p-1); i++){
                currentMuppet = currentMuppet.next;
            }
            currentMuppet.next = m;
            size++;
        } else { //Fool proof 'p'
            System.out.println(p + " is geen geldige invoer.");
        }
    }

    /* Methods pop(int p)
    This methods deletes and returns the muppet at place 'p'. First we check if the list is empty and then if 'p' is within the boundries of the list
    (between 1 and the size of the list). If so, there are three posiblities, 'p' represent the first, the last of a muppet somewhere in between. 
    Based on this information the field 'next' of the muppets adjacent to place 'p' are changed to remove the muppet at place 'p'. This muppet is then returned.
    This method is made fool proof.
    */
    public Muppet pop(int p){
        if(size == 0){ //If list is empty
            System.out.println("De lijst is leeg dus er kan niets verwijdert worden.");
            return null;
        } 
        
        if(p < 1) { //If 'p' is not within the boundries of the list
            System.out.println(p + "is geen geldige invoer");
            return null;
        }

        if(p == 1){ //If 'p' represents the first muppet
            currentMuppet = muppetStart;
            muppetStart = muppetStart.next;
            size--;
            return currentMuppet;   
        } else if ((p > 1) && (p < size())){ //If 'p' represents a muppet somewhere in the middle
            currentMuppet = muppetStart;
            for(int i = 1; i < p; i++){
                if(i == (p-1)){
                tempMuppet = currentMuppet.next;
                currentMuppet.next = currentMuppet.next.next;
                } else {
                currentMuppet = currentMuppet.next;
                }
            }
            size--;
            return tempMuppet;
        } else if (p == size){ //If 'p' represents the last muppet
            currentMuppet = muppetStart;
            for (int i = 1; i < (p-1); i++){
                currentMuppet = currentMuppet.next;
            }
            tempMuppet = currentMuppet.next;
            currentMuppet.next = null;
            size--;
            return tempMuppet;
        }  else { //Fool proof 'p'
            System.out.println(p + " is geen geldige invoer");
            return null;
        }               
    }

    /*Method pop(String s)
    This method deletes muppet with name 's' from the list and returns this. First we check if the list is empty. Then we check if a muppet with name 's' is in the list.
    After that we loop through the list to get the index of the muppet. Finally we call pop(int p) to delete and return the muppet.
    */
    public Muppet pop(String s){
        if(size != 0){ //If list is not empty
            if(peek(s) != null){ //If muppet exists in the list
                currentMuppet = muppetStart;
                count = 1;
                while(currentMuppet.getNaam() != s){ //loop through list
                    currentMuppet = currentMuppet.next;
                    count++;
                }
                return pop(count); 
            } else { //If muppet does not exists in the list
                return null;
            }  
        } else { //If list is empty
            System.out.println("De lijst is leeg dus er kan niets verwijdert worden.");
            return null;
        }
    }

    /* Method peek(String s)
    This method return a pointer to muppet with name 's'. We loop through the list to check if the muppet exists in the list, if so we return a pointer to this
    muppet. If not, we give let the user know this muppet does not exist in the list and return null.
    */
    public Muppet peek(String s){
        currentMuppet = muppetStart;
        while(currentMuppet.getNaam() != s){ //loop door lijst, check naam
            if(currentMuppet.next == null){ //als laatste muppet, return null
                System.out.println("Er staat geen muppet " + s + " in de lijst.");
                return null;
            }
            currentMuppet = currentMuppet.next;
        }
        return currentMuppet;
    }

    /* Method peek(int n)
    This methods returns a pointer to the muppet at place 'n'. First we check is n is within the boundries of the list (between 1 and the lenght of the list). 
    If 'n' is a valid input we go throught the list until we get to place 'n' and then return the pointer to this muppet.
    */
    public Muppet peek(int n){
        if((size() >= n) && (n > 0)){ //If 'n' is a valid input
            currentMuppet = muppetStart;
            for(int i = 1; i < n; i++){ //Go to place 'n'
                currentMuppet = currentMuppet.next;
            }
            System.out.println("Op plaats " + n + " staat muppet " + currentMuppet.getNaam());
            return currentMuppet;
        } else {//If 'n' is an invalid input
            System.out.println(n + " is groter dan de lijst of niet groter dan nul.");
            return null;
        }
    }

    /* Method size()
    This method returns the size of the list.
    */
    public int size(){
        return size;
    }
}

class Stack{
    private LinkedList linkedListStack;

    Stack(LinkedList linkedListStack){
        setLinkedList(linkedListStack);
    }

    public void setLinkedList(LinkedList linkedListStack) {
        this.linkedListStack = linkedListStack;
    }

    //This methods pushes 'm' on top of the other muppets
    void push(Muppet m){
        linkedListStack.push(m, 1); //lijst start op 1
    }

    //This methods pops 'm' from the top and return this muppet
    Muppet pop(){
        if(size() == 0){
            System.out.println("De Stack is leeg dus er kan niets verwijdert worden");
            return null;
        } else {
            return linkedListStack.pop(1);
        }
    }

    //This methods checks if the stack is empty. If not, we print the contents of the stack
    void print(){
        if(linkedListStack.size() == 0){
            System.out.println("De Stack is leeg");
        } else {
        linkedListStack.print();
        }
    }

    //This methods returns the size of the stack
    int size(){
        return linkedListStack.size();
    }
}

class Queue{
    LinkedList linkedListQueue;

    Queue(LinkedList linkedListQueue){
        setLinkedListQueue(linkedListQueue);
    }

    public void setLinkedListQueue(LinkedList linkedListQueue) {
        this.linkedListQueue = linkedListQueue;
    }

    //This methods pushes 'm' at the end of the queue
    void push(Muppet m){
        linkedListQueue.push(m, (size()+1));
    }

    //This methods pops the first muppet in the queue and returns this
    public Muppet pop(){
        return linkedListQueue.pop(1);
    }

    //This methods checks if the queue is empty. If not, it prints the contents of the queue
    public void print(){
        if(linkedListQueue.size() == 0){
            System.out.println("De Queue is leeg");
        } else {
        linkedListQueue.print();
        }
    }

    //This methods returns the size of the queue
    public int size(){
        return linkedListQueue.size();
    }
}