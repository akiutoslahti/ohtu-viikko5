package ohtu;

public class TennisGame {
    
    private final int SCORE_LIMIT = 3;
    private final int ADVANTAGE = 1;

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        if (gameIsEven()) {
            return getEvenScore();
        } else if (gameIsAtAdvantageOrWin()) {
            return getAdvantageOrWinScore();
        }
        return getScoreAtPoint(player1Score) + "-" + getScoreAtPoint(player2Score);
    }

    private boolean gameIsEven() {
        return player1Score == player2Score;
    }

    private String getEvenScore() {
        if (player1Score <= SCORE_LIMIT) {
            return getScoreAtPoint(player1Score) + "-All";
        }
        return getScoreAtPoint(player1Score);
    }

    private boolean gameIsAtAdvantageOrWin() {
        return player1Score > SCORE_LIMIT || player2Score > SCORE_LIMIT;
    }

    private String getAdvantageOrWinScore() {
        String score = "";
        int difference = player1Score - player2Score;
        if (Math.abs(difference) == ADVANTAGE) {
            score += "Advantage ";
        } else {
            score += "Win for ";
        }
        if (difference < 0) {
            score += player2Name;
        } else {
            score += player1Name;
        }
        return score;
    }

    private String getScoreAtPoint(int point) {
        switch (point) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }
    }
}
