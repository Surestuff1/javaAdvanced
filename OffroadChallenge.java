import java.nio.charset.CodingErrorAction;
import java.util.*;
import java.util.stream.Collectors;

public class OffroadChallenge {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        Deque<Integer> initialFuel = new ArrayDeque<>();
        Deque<Integer> additionalConsumption = new ArrayDeque<>();

        Deque<Integer> fuelNeeded=new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(initialFuel::push);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(additionalConsumption::offer);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(fuelNeeded::offer);

        int count=0;
        int top=1;

        int covered=0;
        boolean isTrue=true;
        while(covered<4 && initialFuel.size()>=0 && additionalConsumption.size()>=0 && fuelNeeded.size()>=0){
            int fuel=initialFuel.pop();
            int additionalfuel=additionalConsumption.poll();
            int neededFuel=fuelNeeded.poll();

            int difference=fuel-additionalfuel;
            if(difference-neededFuel>=0){
                System.out.printf("John has reached: Altitude %d",covered+1);
                System.out.println();
                covered++;
            }
            else{
                System.out.printf("John did not reach: Altitude %d",covered+1);
                System.out.println();
                break;
            }
        }
        if(covered>0){
            if(covered+1==4){
                System.out.println("John has reached all the altitudes and managed to reach the top!");

            }
            else if(covered+1<4){
                System.out.print("John failed to reach the top.\n");
                System.out.print("Reached altitudes: ");
                for (int i = 0; i < covered; i++) {
                System.out.printf("Altitude %d",count+1);
                count++;
                if(count<covered) {
                    System.out.print(", ");
                    }
                }
            }
        }
        else if(covered<=0){
            System.out.println("John failed to reach the top.\nJohn didn't reach any altitude.");
        }

    }
}