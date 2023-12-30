package cu.innovat.psigplus.cim.questions;

import java.util.Comparator;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 15/11/23
 */
public class QuestionComparator implements Comparator<Question> {
    /**
     *
     */
    public QuestionComparator(){}

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Question o1, Question o2) {

        if( o1.getUsed() < o2.getUsed() ) return -1;
        if( o1.getUsed() > o2.getUsed() ) return 1;
        if(o1.getLastUse() < o2.getLastUse()) return -1;
        if(o1.getLastUse() > o2.getLastUse()) return 1;
        return 0;

    }
}