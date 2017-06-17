package persistenceAPI;

/**
 * Created by mcada on 6/17/17.
 */
public class Record {
   private String nick;
   private int score;

   public Record(String nick, int score) {
      this.nick = nick;
      this.score = score;
   }

   public String getNick() {
      return nick;
   }

   public void setNick(String nick) {
      this.nick = nick;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   @Override
   public String toString() {
      return "Record{" +
            "nick='" + nick + '\'' +
            ", score=" + score +
            '}';
   }
}
