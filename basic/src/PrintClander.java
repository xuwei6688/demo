import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 打印日历
 */
public abstract class PrintClander {
    public static void main(String[] args)  {
        LocalDate date = LocalDate.now();
        int monthValue = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today - 1);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int week = dayOfWeek.getValue();

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < week; i++){
            System.out.print("    ");
        }
        while (date.getMonthValue() == monthValue){
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            }
            else{
                System.out.print(" ");
            }
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }
    }
}


