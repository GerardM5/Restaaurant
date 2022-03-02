import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try {
            Restaurant restaurant = new Restaurant(askForName());

            while (!isFull(restaurant)) {
                boolean wantsToEnterPeople = showMenu();
                procesComensals(restaurant, wantsToEnterPeople);
            }
            System.out.println("restaurante completo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isFull(Restaurant restaurant) {
        return restaurant.getCurrentCapacity() >= Restaurant.getMaxCapacity();
    }

    private static boolean showMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quieres que entren o que salgan los clientes? (1-Entrar/0-Salir)");
        return sc.nextInt()==1;
    }

    private static void procesComensals(Restaurant restaurant, boolean wantsToEnterPeople) throws Exception {

        if (wantsToEnterPeople) {
            int comensals = askForComensals();
            restaurant.addTable(comensals);
        } else {
            boolean isAcompannied;
            do {
                int table = askWhatTableWantToLeave();
                leaveTable(restaurant, table);
                isAcompannied = askIfIsAcompannied();
            } while (isAcompannied);

        }
        printCurrentCapacity(restaurant);

    }

    private static boolean askIfIsAcompannied() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veniais con otra mesa?(1-Si/0-No)");
        return sc.nextInt()==1;
    }

    private static void leaveTable(Restaurant restaurant, int table) throws Exception {
        restaurant.deleteTable(table);
        System.out.println("Mesa " + table + " se ha ido");
    }

    private static int askWhatTableWantToLeave() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que mesa ha terminado y se va?");
        return sc.nextInt();

    }


    private static void printCurrentCapacity(Restaurant restaurant) {
        System.out.println(restaurant.getCurrentCapacity() + " de " + Restaurant.getMaxCapacity());
        System.out.println(restaurant.getTables());

    }

    private static int askForComensals() {
        System.out.println("Cuantas personas comeran? ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private static String askForName() {
        System.out.println("Nombre del restaurante: ");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
