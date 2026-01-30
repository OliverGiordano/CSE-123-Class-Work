class Main{
    public static void main(String[] args){
        ConnectFour g = new ConnectFour();
        while(g.getWinner() == -1){
            g.makeMove(g.getMove())
        }
    }
}
