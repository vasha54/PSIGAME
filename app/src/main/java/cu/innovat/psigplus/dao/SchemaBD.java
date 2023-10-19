package cu.innovat.psigplus.dao;

import android.provider.BaseColumns;
/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 15/10/23
 */
public final class SchemaBD {

    public static final String DATABASE_NAME = "psigame_plus.db";
    public static final int DATABASE_VERSION = 1;

    public static class PlayerTable implements BaseColumns {
        public static final String TABLE_NAME = "player";
        public static final String C_ID = "id";
        public static final String C_NAME = "name";
        public static final String C_SURNAME = "surname";
        public static final String C_CI = "ci";
        public static final String C_PHONE_NUMBER = "phone_number";
        public static final String C_EMIE = "emie";
        public static final String C_ID_GROUP = "id_group";
        public static final String C_ACTIVE = "active";
    }

    public static class LevelTable implements BaseColumns {
        public static final String TABLE_NAME = "level";
        public static final String C_ID = "id";
        public static final String C_NAME = "name";
        public static final String C_SLUG = "slug";
    }

    public static class AcademicGroupTable implements BaseColumns {
        public static final String TABLE_NAME = "academic_group";
        public static final String C_ID = "id";
        public static final String C_NAME = "name";
        public static final String C_SLUG = "slug";
    }

    public static class QuizzTable implements BaseColumns {
        public static final String TABLE_NAME = "quizz";
        public static final String C_ID = "id";
        public static final String C_ID_PLAYER = "id_player";
        public static final String C_ID_LEVEL = "id_level";
        public static final String C_DATE = "date";
    }

    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "question";
        public static final String C_ID = "id";
    }

    public static class TrueOrFalseTable implements BaseColumns {
        public static final String TABLE_NAME = "true_or_false_question";
        public static final String C_ID = "id_question";
    }

    public static class QuizzQuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "quizz_question";
    }

    public static class SingleChoiseTable implements BaseColumns {
        public static final String TABLE_NAME = "single_choise_question";
        public static final String C_ID = "id_question";
    }

    public static class MultipleChoiseTable implements BaseColumns {
        public static final String TABLE_NAME = "multiple_choise_question";
        public static final String C_ID = "id_question";
    }

    public static class JudgmentTable implements BaseColumns {
        public static final String TABLE_NAME = "judgment";
        public static final String C_ID = "id";
    }

    public static class WordTable implements BaseColumns {
        public static final String TABLE_NAME = "word";
        public static final String C_ID = "id";
    }

    public static class ChoiseJudgmentTable implements BaseColumns {
        public static final String TABLE_NAME = "choise_judgment";
    }

    public static class CompleteWordTable implements BaseColumns {
        public static final String TABLE_NAME = "complete_word_question";
        public static final String C_ID = "id_question";
    }

    public static class CompleteWordWordTable implements BaseColumns {
        public static final String TABLE_NAME = "complete_word_word";
    }
}
