import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Square {

    List<Integer> possibilities = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9)) ; // list with integers 1 - 9
    int x;
    int y;


    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Square(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.possibilities =  new ArrayList(Arrays.asList(number));
    }

    public boolean delete(List<Integer> numbers){
        boolean changed = false;
        if(!solved()){
            for (int number:
                    numbers) {
                changed |= this.possibilities.contains(number);
                this.possibilities.remove(new Integer(number));


            }

            if(possibilities.size()==1) {
                System.out.println("only one possibility left: "+possibilities.get(0));
            }
            if(possibilities.size()==0) {
                System.out.println("ERROR: No possibilities left. ");
            }
        }
        return changed;
    }

    boolean solved(){
        return this.possibilities.size() == 1;
    }

    void solve(int number){
        this.possibilities = new ArrayList(Arrays.asList(number));
        System.out.println("set number: "+possibilities.get(0));
    }


}
