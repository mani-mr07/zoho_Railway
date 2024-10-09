import java.util.*;

public class Reservation {
   private String name;
   private   boolean isAlllocated;
   private int seatNumber;
   private Character berthtype;
   private  String ticketType;

   int totalSeat=2;

   static ArrayList<Reservation>confirmlist=new ArrayList<>();
   static  Queue<Reservation>RAClist=new LinkedList<>();
   static Queue<Reservation>Waitinglist=new LinkedList<>();
   static HashMap<Character, Integer> berths = new HashMap<Character, Integer>();

   static {
        berths.put('u', 0);
        berths.put('m', 0);
        berths.put('l', 0);
    }
    public Reservation(String name) {
        this.name = name;
    }
    public void reserve(Character berthtype){
        getAvailableBerth(berthtype);
    }
    private void getAvailableBerth(Character berthtype) {
        for(Map.Entry<Character,Integer>entries: berths.entrySet()){
            if(entries.getKey().equals(berthtype) && entries.getValue()<2){
                this.seatNumber= entries.getValue()+1;
                this.berthtype=entries.getKey();
                this.isAlllocated=true;
                getTicketType();
                berths.put(entries.getKey(),entries.getValue()+1);
                return;
            }
        }
        for(Map.Entry<Character,Integer>entries: berths.entrySet()){
            if(entries.getValue()<2){
            this.seatNumber= entries.getValue()+1;
            this.berthtype=entries.getKey();
            this.isAlllocated=true;
            getTicketType();
            berths.put(entries.getKey(),entries.getValue()+1);
            return;
            }
        }
        getTicketType();
    }

    private void getTicketType() {
        if(confirmlist.size()<6){
            confirmlist.add(this);
            this.ticketType="Confirm";
        }
        else if(RAClist.size()<7){
            this.berthtype='N';
            RAClist.offer(this);
            this.ticketType="rac";
        }
        else if(Waitinglist.size()<7){
            this.berthtype='N';
            Waitinglist.add(this);
            this.ticketType="waiting";
        }
        printData();
    }

    private void printData(){
        System.out.println(this.name+" "+this.seatNumber+" "+this.berthtype+" "+ this.isAlllocated+" "+this.ticketType+" ");
       for(Map.Entry<Character,Integer>entry: berths.entrySet()){
           System.out.println(entry.getKey()+" "+entry.getValue());
       }
       System.out.println(confirmlist);
       System.out.println(RAClist);
       System.out.println(Waitinglist);
    }

    public void cancel(){
       System.out.print("After Cancelling the ticket"+this.ticketType);
        if((this.ticketType).equals("Confirm")) {
            confirmlist.remove(this);
            confirmlist.add(RAClist.poll());
            if(Waitinglist.size()>1){
            RAClist.offer(Waitinglist.poll());}
        }
        else if ((this.ticketType).equals("rac")) {
            RAClist.remove(this);
            RAClist.offer(Waitinglist.poll());
        }
        printData();
    }
}
