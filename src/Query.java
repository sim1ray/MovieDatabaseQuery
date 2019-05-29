import java.util.HashMap;
import java.util.HashSet;

public interface Query {
    HashSet<Integer> execute(HashMap<String, MovieProperty> hm);
    //String getKeyName();
}
