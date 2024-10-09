public class Main {
    public static void main(String[] args) {
    Reservation mani=new Reservation("mani");
    mani.reserve('u');
        Reservation alagu=new Reservation("alagu");
        alagu.reserve('u');
        Reservation nagu=new Reservation("nagu");
        nagu.reserve('u');
        Reservation siva=new Reservation("siva");
        siva.reserve('m');
        Reservation karthick=new Reservation("karthick");
        karthick.reserve('m');
        Reservation bala=new Reservation("bala");
        bala.reserve('m');
        Reservation vish=new Reservation("vish");
        vish.reserve('m');
        bala.cancel();
    }
}