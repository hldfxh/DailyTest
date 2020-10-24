import java.lang.String;

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int[][] pathMap = new int[matrix.length][matrix[0].length];
        int max = 0;

        for(int i = 0;i<matrix.length;i++){
            for(int j =0;j<matrix[0].length;j++){
                max = Math.max(max,getLength(matrix,i,j,pathMap));
            }
        }
        return max;
    }

    private int getLength(int[][] matrix, int x, int y, int[][] pathMap){
        if(pathMap[x][y] != 0)
            return pathMap[x][y];

        int max = 1;

        if(x-1>=0){
            if(y-1>=0 && matrix[x-1][y-1]>matrix[x][y]){
                max = Math.max(max,1+getLength(matrix,x-1,y-1,pathMap));
            }
            if(y+1<matrix[0].length && matrix[x-1][y+1]>matrix[x][y] ){
                max = Math.max(max,1+getLength(matrix,x-1,y+1,pathMap));
            }
        }
        if(x+1<matrix.length){
            if(y-1>=0 && matrix[x+1][y-1]>matrix[x][y]){
                max = Math.max(max,1+getLength(matrix,x+1,y-1,pathMap));
            }
            if(y+1<matrix[0].length && matrix[x+1][y+1]>matrix[x][y]){
                max = Math.max(max,1+getLength(matrix,x+1,y+1,pathMap));
            }
        }
        pathMap[x][y] = max;
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}});
    }
}
