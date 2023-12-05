package cu.innovat.psigplus.dao;

import android.provider.BaseColumns;
import cu.innovat.psigplus.cim.questions.Question;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 15/10/23
 */
public final class SchemaBD {

    public static final String DATABASE_NAME = "psigame_plus.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TAG_DATABASE = "TAG_DB_PSIGAME_PLUS";



   public static class LevelTable implements BaseColumns {
        public static final String TABLE_NAME = "level";
        public static final String C_ID = "id";
        public static final String C_NAME = "name";
        public static final String C_CARDINAL = "cardinal";
        public static final String C_SLUG = "slug";
    }
    public static final String SQL_CREATE_LEVEL_TABLE = "CREATE TABLE IF NOT EXISTS "+LevelTable.TABLE_NAME +" ( "+
            LevelTable.C_ID+" TEXT NOT NULL,"+
            LevelTable.C_NAME+" TEXT NOT NULL,"+
            LevelTable.C_SLUG+" TEXT NOT NULL,"+
            LevelTable.C_CARDINAL+" INTEGER NOT NULL,"+
            "PRIMARY KEY("+ LevelTable.C_ID +")"+
            " UNIQUE ( "+LevelTable.C_SLUG+" , "+LevelTable.C_CARDINAL+") "+
            ")";
    public static final String SQL_EXIST_LEVEL_WITH_SLUG = "SELECT EXISTS (SELECT * FROM "+LevelTable.TABLE_NAME+
            " WHERE "+LevelTable.C_SLUG+"=? LIMIT 1)";
    public static final String SQL_EXIST_LEVEL_WITH_ID = "SELECT EXISTS (SELECT * FROM "+LevelTable.TABLE_NAME+
            " WHERE "+LevelTable.C_ID+"=? LIMIT 1)";
    public static final String SQL_SELECT_ID_WITH_SLUG = "SELECT "+LevelTable.C_ID+" FROM "+LevelTable.TABLE_NAME+
            " WHERE "+LevelTable.C_SLUG+"=? LIMIT 1";
    public static final String SQL_DROP_LEVEL_TABLE = "DROP TABLE IF EXISTS "+LevelTable.TABLE_NAME;



    public static class AcademicGroupTable implements BaseColumns {
        public static final String TABLE_NAME = "academic_group";
        public static final String C_ID = "id";
        public static final String C_NAME = "name";
        public static final String C_SLUG = "slug";
    }
    public static final String SQL_CREATE_ACADEMIC_GROUP_TABLE = "CREATE TABLE IF NOT EXISTS "+AcademicGroupTable.TABLE_NAME +" ( "+
            AcademicGroupTable.C_ID+" TEXT NOT NULL,"+
            AcademicGroupTable.C_NAME+" TEXT NOT NULL,"+
            AcademicGroupTable.C_SLUG+" TEXT NOT NULL,"+
            "PRIMARY KEY ("+ AcademicGroupTable.C_ID +") "+
            " UNIQUE ( "+AcademicGroupTable.C_SLUG+" ) "+
            ")";
    public static final String SQL_EXIST_ACADEMIC_GROUP_WITH_SLUG = "SELECT EXISTS (SELECT * FROM "+AcademicGroupTable.TABLE_NAME+
            " WHERE "+AcademicGroupTable.C_SLUG+"=? LIMIT 1)";
    public static final String SQL_DROP_ACADEMIC_GROUP_TABLE = "DROP TABLE IF EXISTS "+AcademicGroupTable.TABLE_NAME;



    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "question";
        public static final String C_ID = "id";
        public static final String C_STATEMENT = "statement";
        public static final String C_LEVEL = "level";
        public static final String C_AMOUNT_USE = "amount_use";
        public static final String C_LAST_USE = "last_use";
    }
    public static final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE IF NOT EXISTS "+QuestionTable.TABLE_NAME +" ( "+
            QuestionTable.C_ID+" TEXT NOT NULL,"+
            QuestionTable.C_STATEMENT+" TEXT NOT NULL,"+
            QuestionTable.C_LEVEL+" TEXT NOT NULL,"+
            QuestionTable.C_AMOUNT_USE+" INTEGER NOT NULL,"+
            QuestionTable.C_LAST_USE+" INTEGER NOT NULL,"+
            "PRIMARY KEY ("+ QuestionTable.C_ID +"), "+
            "FOREIGN KEY("+ QuestionTable.C_LEVEL +") REFERENCES "+LevelTable.TABLE_NAME+"("+ LevelTable.C_ID +")"+
            ")";
    public static final String SQL_EXIST_QUESTION_WITH_ID = "SELECT EXISTS (SELECT * FROM "+QuestionTable.TABLE_NAME+
            " WHERE "+QuestionTable.C_ID+"=? LIMIT 1)";
    public static final String SQL_DROP_QUESTION_TABLE = "DROP TABLE IF EXISTS "+QuestionTable.TABLE_NAME;



    public static class TrueOrFalseTable implements BaseColumns {
        public static final String TABLE_NAME = "true_or_false_question";
        public static final String C_ID = "id_question";
        public static final String C_RESULT = "result";
    }
    public static final String SQL_CREATE_TRUE_OR_FALSE_TABLE = "CREATE TABLE IF NOT EXISTS "+TrueOrFalseTable.TABLE_NAME +" ( "+
            TrueOrFalseTable.C_ID+" TEXT NOT NULL,"+
            TrueOrFalseTable.C_RESULT+" TEXT NOT NULL,"+
            "PRIMARY KEY ("+ TrueOrFalseTable.C_ID +"),"+
            "FOREIGN KEY("+ TrueOrFalseTable.C_ID +") REFERENCES "+QuestionTable.TABLE_NAME+"( "+QuestionTable.C_ID+" )"+
            ")";
    public static final String SQL_EXIST_TRUE_OR_FALSE_WITH_ID = "SELECT EXISTS (SELECT * FROM "+TrueOrFalseTable.TABLE_NAME+
            " WHERE "+TrueOrFalseTable.C_ID+"=? LIMIT 1)";
    public static final String SQL_SELECT_ALL_Q_TRUE_OR_FALSE_TABLE_WITH_ID_LEVEL = "SELECT * FROM "+TrueOrFalseTable.TABLE_NAME+
            " INNER JOIN "+ QuestionTable.TABLE_NAME +" ON "+TrueOrFalseTable.TABLE_NAME+"."+TrueOrFalseTable.C_ID+"="+
            QuestionTable.TABLE_NAME+"."+QuestionTable.C_ID+" WHERE "+QuestionTable.C_LEVEL+"=?";
    public static final String SQL_DROP_TRUE_OR_FALSE_TABLE = "DROP TABLE IF EXISTS "+TrueOrFalseTable.TABLE_NAME;



    public static class MultipleChoiseTable implements BaseColumns {
        public static final String TABLE_NAME = "multiple_choise_question";
        public static final String C_ID = "id_question";
    }
    public static final String SQL_CREATE_MULTIPLE_CHOISE_TABLE = "CREATE TABLE IF NOT EXISTS "+MultipleChoiseTable.TABLE_NAME + " ( "+
            MultipleChoiseTable.C_ID+" TEXT NOT NULL,"+
            "PRIMARY KEY ("+ MultipleChoiseTable.C_ID +"),"+
            "FOREIGN KEY("+ MultipleChoiseTable.C_ID +") REFERENCES "+QuestionTable.TABLE_NAME+"( "+QuestionTable.C_ID+" )"+
            ")";
    public static final String SQL_DROP_MULTIPLE_CHOISE_TABLE = "DROP TABLE IF EXISTS "+MultipleChoiseTable.TABLE_NAME;



    public static class SentenceTable implements BaseColumns {
        public static final String TABLE_NAME = "sentence";
        public static final String C_ID = "id";
        public static final String C_SENTENCE = "sentence";
        public static final String C_SLUG = "slug";
    }
    public static final String SQL_CREATE_SENTENCE_TABLE = "CREATE TABLE IF NOT EXISTS "+SentenceTable.TABLE_NAME+" ( "+
            SentenceTable.C_ID+" TEXT NOT NULL,"+
            SentenceTable.C_SENTENCE+" TEXT NOT NULL,"+
            SentenceTable.C_SLUG+" TEXT NOT NULL,"+
            "PRIMARY KEY ("+ SentenceTable.C_ID +"), "+
            " UNIQUE ( "+SentenceTable.C_SLUG+" ) "+
            ")";
    public static final String SQL_EXIST_SENTENCE_WITH_ID = "SELECT EXISTS (SELECT * FROM "+SentenceTable.TABLE_NAME+
            " WHERE "+SentenceTable.C_ID+"=? LIMIT 1)";
    public static final String SQL_DROP_SENTENCE_TABLE = "DROP TABLE IF EXISTS "+SentenceTable.TABLE_NAME;



    public static class MultipleChoiseSentenceTable implements BaseColumns{
        public static final String TABLE_NAME = "multiple_choise_sentence";
        public static final String C_ID_QUESTION = "id_question";
        public static final String C_ID_SENTENCE = "id_sentence";
        public static final String C_CHOISE = "choise";
    }
    public static final String SQL_CREATE_MULTIPLE_CHOISE_SENTENCE_TABLE = "CREATE TABLE IF NOT EXISTS "+MultipleChoiseSentenceTable.TABLE_NAME+" ( "+
            MultipleChoiseSentenceTable.C_ID_QUESTION+" TEXT NOT NULL,"+
            MultipleChoiseSentenceTable.C_ID_SENTENCE+" TEXT NOT NULL,"+
            MultipleChoiseSentenceTable.C_CHOISE+" INTEGER NOT NULL,"+
            "PRIMARY KEY ("+ MultipleChoiseSentenceTable.C_ID_SENTENCE +","+MultipleChoiseSentenceTable.C_ID_QUESTION+") ,"+
            "FOREIGN KEY("+ MultipleChoiseSentenceTable.C_ID_QUESTION+") REFERENCES "+MultipleChoiseTable.TABLE_NAME+
            "( "+MultipleChoiseTable.C_ID+" ),"+
            "FOREIGN KEY("+ MultipleChoiseSentenceTable.C_ID_SENTENCE +") REFERENCES "+SentenceTable.TABLE_NAME+"( "+SentenceTable.C_ID+" )"+
                    ")";
    public static final String SQL_DROP_MULTIPLE_CHOISE_SENTENCE_TABLE = "DROP TABLE IF EXISTS "+MultipleChoiseSentenceTable.TABLE_NAME;
/*
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
    public static final String SQL_CREATE_PLAYER_TABLE = "CREATE TABLE IF NOT EXISTS "+PlayerTable.TABLE_NAME +" ( "+
            PlayerTable.C_ID+" TEXT NOT NULL,"+
            PlayerTable.C_NAME+" TEXT NOT NULL,"+
            PlayerTable.C_SURNAME+" TEXT NOT NULL,"+
            PlayerTable.C_CI+" TEXT NOT NULL,"+
            PlayerTable.C_PHONE_NUMBER+" INTEGER NOT NULL,"+
            PlayerTable.C_EMIE+" TEXT NOT NULL,"+
            PlayerTable.C_ID_GROUP+" TEXT NOT NULL,"+
            PlayerTable.C_ACTIVE+" INTEGER NOT NULL,"+
            "PRIMARY KEY("+ PlayerTable.C_ID +")"+
            ")";


    public static final String SQL_DROP_PLAYER_TABLE = "DROP TABLE IF EXISTS "+PlayerTable.TABLE_NAME;









    public static class QuizzTable implements BaseColumns {
        public static final String TABLE_NAME = "quizz";
        public static final String C_ID = "id";
        public static final String C_ID_PLAYER = "id_player";
        public static final String C_ID_LEVEL = "id_level";
        public static final String C_COUNT_ANSWER = "count_answer";
        public static final String C_DATE = "date";
    }

    public static final String SQL_CREATE_QUIZZ_TABLE = "";
    public static final String SQL_DROP_QUIZZ_TABLE = "DROP TABLE IF EXISTS "+QuizzTable.TABLE_NAME;









    public static class QuizzQuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "quizz_question";
    }

    public static final String SQL_CREATE_QUIZZ_QUESTION_TABLE = "";
    public static final String SQL_DROP_QUIZZ_QUESTION_TABLE = "DROP TABLE IF EXISTS "+QuizzQuestionTable.TABLE_NAME;

    public static class SingleChoiseTable implements BaseColumns {
        public static final String TABLE_NAME = "single_choise_question";
        public static final String C_ID = "id_question";
    }

    public static final String SQL_CREATE_SINGLE_CHOISE_TABLE = "";
    public static final String SQL_DROP_SINGLE_CHOISE_TABLE = "DROP TABLE IF EXISTS "+SingleChoiseTable.TABLE_NAME;





    public static class JudgmentTable implements BaseColumns {
        public static final String TABLE_NAME = "judgment";
        public static final String C_ID = "id";
    }

    public static final String SQL_CREATE_JUDGMENT_TABLE = "";
    public static final String SQL_DROP_JUDGMENT_TABLE = "DROP TABLE IF EXISTS "+JudgmentTable.TABLE_NAME;

    public static class WordTable implements BaseColumns {
        public static final String TABLE_NAME = "word";
        public static final String C_ID = "id";
    }

    public static final String SQL_CREATE_WORD_TABLE = "";
    public static final String SQL_DROP_WORD_TABLE = "DROP TABLE IF EXISTS "+WordTable.TABLE_NAME;

    public static class ChoiseJudgmentTable implements BaseColumns {
        public static final String TABLE_NAME = "choise_judgment";
    }

    public static final String SQL_CREATE_CHOISE_JUDGMENT_TABLE = "";
    public static final String SQL_DROP_CHOISE_JUDGMENT_TABLE = "DROP TABLE IF EXISTS "+ChoiseJudgmentTable.TABLE_NAME;

    public static class CompleteWordTable implements BaseColumns {
        public static final String TABLE_NAME = "complete_word_question";
        public static final String C_ID = "id_question";
    }

    public static final String SQL_CREATE_COMPLETE_WORD_TABLE = "";
    public static final String SQL_DROP_COMPLETE_WORD_TABLE = "DROP TABLE IF EXISTS "+CompleteWordTable.TABLE_NAME;

    public static class CompleteWordWordTable implements BaseColumns {
        public static final String TABLE_NAME = "complete_word_word";
    }

    public static final String SQL_CREATE_COMPLETE_WORD_WORD_TABLE = "";
    public static final String SQL_DROP_COMPLETE_WORD_WORD_TABLE = "DROP TABLE IF EXISTS "+CompleteWordWordTable.TABLE_NAME;*/
}
