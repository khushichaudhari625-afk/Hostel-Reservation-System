import java.io.*;
import java.util.*;

class Room {
    int no;
    String type;
    double price;
    boolean available = true;

    Room(int no, String type, double price) {
        this.no = no;
        this.type = type;
        this.price = price;
    }
}

public class HotelReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static Room[] rooms = {
        new Room(101, "Standard", 1500),
        new Room(102, "Deluxe", 2500),
        new Room(103, "Suite", 4000)
    };

    static int bookingId = 1001;
    static String customer;
    static Room bookedRoom;
    static int nights;

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("\n--- HOTEL RESERVATION SYSTEM ---");
            System.out.println("1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Booking Details");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            if (ch == 1) {
                for (Room r : rooms)
                    if (r.available)
                        System.out.println(r.no + " " + r.type +
                                " Rs." + r.price);
            }

            else if (ch == 2) {
                System.out.print("Enter name: ");
                customer = sc.next();

                System.out.print("Enter room no: ");
                int no = sc.nextInt();

                for (Room r : rooms) {
                    if (r.no == no && r.available)
                        bookedRoom = r;
                }

                if (bookedRoom == null) {
                    System.out.println("Room not available!");
                    continue;
                }

                System.out.print("Enter nights: ");
                nights = sc.nextInt();

                double total = bookedRoom.price * nights;

                System.out.println("Total: Rs." + total);
                System.out.println("Payment successful!");

                bookedRoom.available = false;

                FileWriter f = new FileWriter("bookings.txt", true);
                f.write(bookingId + "," + customer + "," +
                        bookedRoom.no + "," + nights + "," + total + "\n");
                f.close();

                System.out.println("Booking successful!");
                System.out.println("Booking ID: " + bookingId);
                bookingId++;
            }

            else if (ch == 3) {
                if (bookedRoom != null) {
                    bookedRoom.available = true;
                    bookedRoom = null;
                    System.out.println("Booking cancelled!");
                } else {
                    System.out.println("No booking found!");
                }
            }

            else if (ch == 4) {
                if (bookedRoom != null) {
                    System.out.println("\nBooking ID: " + (bookingId - 1));
                    System.out.println("Customer: " + customer);
                    System.out.println("Room: " + bookedRoom.no);
                    System.out.println("Type: " + bookedRoom.type);
                    System.out.println("Nights: " + nights);
                    System.out.println("Total: Rs." +
                            bookedRoom.price * nights);
                } else {
                    System.out.println("No booking found!");
                }
            }

            else if (ch == 5) {
                System.out.println("Thank you!");
                break;
            }

            else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
