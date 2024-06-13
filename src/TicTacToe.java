import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private int n;
    private char currentPlayer;

    public TicTacToe(int n) {
        this.n = n;
        this.board = new char[n][n];
        this.currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean placeMark(int row, int col) {
        if (row >= 0 && col >= 0 && row < n && col < n && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkForWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < n; i++) {
            if (checkRowCol(board[i][0], board[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < n; i++) {
            char[] col = new char[n];
            for (int j = 0; j < n; j++) {
                col[j] = board[j][i];
            }
            if (checkRowCol(board[0][i], col)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        char[] mainDiagonal = new char[n];
        char[] antiDiagonal = new char[n];
        for (int i = 0; i < n; i++) {
            mainDiagonal[i] = board[i][i];
            antiDiagonal[i] = board[i][n - 1 - i];
        }
        return (checkRowCol(board[0][0], mainDiagonal) || checkRowCol(board[0][n - 1], antiDiagonal));
    }

    private boolean checkRowCol(char c, char[] line) {
        if (c == '-') {
            return false;
        }
        for (char ch : line) {
            if (ch != c) {
                return false;
            }
        }
        return true;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the board (n): ");
        int n = scanner.nextInt();
        TicTacToe game = new TicTacToe(n);

        System.out.println("Tic-Tac-Toe game started!");
        game.printBoard();

        while (true) {
            int row, col;
            do {
                System.out.println("Player " + game.getCurrentPlayer() + ", enter your move (row and column): ");
                row = scanner.nextInt();
                col = scanner.nextInt();
            } while (!game.placeMark(row, col));

            game.printBoard();

            if (game.checkForWin()) {
                System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                break;
            }

            if (game.isBoardFull()) {
                System.out.println("The game is a tie!");
                break;
            }

            game.changePlayer();
        }

        scanner.close();
    }
}
