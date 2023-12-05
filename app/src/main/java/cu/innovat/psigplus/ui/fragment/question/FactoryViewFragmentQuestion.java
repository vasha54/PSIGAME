package cu.innovat.psigplus.ui.fragment.question;

import cu.innovat.psigplus.cim.questions.MultipleChoise;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.cim.questions.TrueOrFalse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 15/11/23
 */
public class FactoryViewFragmentQuestion {
    public static List<QuestionFragment> getFragments(List<Question> questions){
        List<QuestionFragment> fragments = new ArrayList<QuestionFragment>();
        for(Question q : questions){
            if( q instanceof TrueOrFalse){
                TrueOrFalseFragment f = new TrueOrFalseFragment(q);
                fragments.add(f);
            }else if (q instanceof MultipleChoise){
                MultipleChoiseFragment f = new MultipleChoiseFragment(q);
                fragments.add(f);
            }
        }
        return fragments;
    }
}
