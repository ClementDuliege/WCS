import javax.swing.JPanel;

import data.WorldCup;
import data.RankingWorldCup;

public class RankingWorldCupFrame extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private RankingWorldCup rankingWorldCup;
    private int x,y;

    public RankingWorldCupFrame(WorldCup worldCup, int x,int y) {
        this.rankingWorldCup=worldCup.getRanking();
    }
    
  
}
