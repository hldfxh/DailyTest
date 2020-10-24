import java.lang.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeastRange {

    public static int[] smallestRange(List<List<Integer>> nums) {
        int N = 0;
        for(List<Integer> num : nums) N += num.size();
        int[][] ordered = new int[N][2];
        int i = 0;
        int j = 0;
        for(List<Integer> group : nums){
            for(Integer num : group){
                ordered[i][0] = num;
                ordered[i][1] = j;
                i++;
            }
            j++;
        }
        //Comparator.comparingInt
        Arrays.sort(ordered, Comparator.comparingInt(o -> o[0]));
        int[] ans = new int[2];
        int[] count = new int [nums.size()]; //数组的个数
        int k = 0;i = 0;
        for(j = 0; j < N; j++){ //N 数字的个数
            if(0 == count[ordered[j][1]]++) k++;// count 对应的第几组，成员数++, count在每次有新成员的时候都++，k++
            if(k == nums.size()){//也就是所有的组都至少出现了一遍
                //再从第一个数字开始，从前往后一个个遍历，假如去掉这个数字以后，所有数组的计数仍然都>1,表示它是多余的，往后接着去掉，直到有一个数字去掉以后，某个数组的计数变成了1，表明这个数字不能再去掉了，停止这个去数字的行为
                while (count[ordered[i][1]] > 1) count[ordered[i++][1]]--;
                //此时 i就是起始数字，j就是结束的数字
                if((ans[0] == 0 && ans[1] == 0 )|| ans[1] - ans[0] > ordered[j][0] - ordered[i][0]){
                    ans = new int[]{ordered[i][0],ordered[j][0]};
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(4,10,15,24,26));
        list.add(Arrays.asList(0,9,12,20));
        list.add(Arrays.asList(5,18,22,30));
      //  [(0,1),(4,0),(5,2),(9,1),(10,0),(12,1),(15,0),(18,2),(20,1),(22,2),(24,0),(26,0),(30,2)]

        int[] ints = smallestRange(list);
        System.out.printf("");
    }
}
