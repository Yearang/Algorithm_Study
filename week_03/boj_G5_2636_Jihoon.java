import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2636 {
    //BFS이용
    public static int row, col;
    public static int map[][];
    public static int dx[] = {-1,1,0,0};
    public static int dy[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        row = Integer.parseInt(stz.nextToken());
        col = Integer.parseInt(stz.nextToken());
        map = new int[row][col];
        
        for(int i = 0; i < row; i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++)
                map[i][j] = Integer.parseInt(stz.nextToken());
        }
        
        int time = 0;
        int cheese = count();
        while(count() != 0) {
            air();
            melt();
            time++;
            
            if(count() != 0)
                cheese = count();
        }
        
        System.out.println(time + "\n" + cheese);
    }
    
    //주변에 공기가 있으면 녹음
    public static void melt() {
        int copy[][] = new int[row][col];
        for(int i = 0; i < row; i++)
            copy[i] = map[i].clone();
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(copy[i][j] == 1){
                    for(int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        
                        if(check(x, y) && copy[x][y] == 2)
                            map[i][j] = 0;
                    }
                }
            }
        }
    }
    
    public static int count() {
        int count = 0;
        for(int i = 0; i < row; i++)
            for(int j = 0; j < col; j++)
                if(map[i][j] == 1)
                    count++;
        
        return count;
    }
    
    //공기 2로 바꿔줌
    public static void air() {
        boolean visit[][] = new boolean[row][col];
        map[0][0] = 2;
        Queue<Integer> qx = new LinkedList<Integer>();
        Queue<Integer> qy = new LinkedList<Integer>();
        qx.add(0);
        qy.add(0);
        visit[0][0] = true;
        
        while(!qx.isEmpty()) {
            int x = qx.poll();
            int y = qy.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(check(nx, ny) && !visit[nx][ny] && map[nx][ny] != 1) {
                    visit[nx][ny] = true;
                    qx.offer(nx);
                    qy.offer(ny);
                    map[nx][ny] = 2;
                }
            }
        }
    }
    
    public static boolean check(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}
