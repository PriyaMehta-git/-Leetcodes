class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int ans=0;
        int currentHeight;
        for( int i=0;i<=n;i++){
            currentHeight= (i==n) ?0 : heights[i];

        
       while(!stack.isEmpty() && currentHeight<heights[stack.peek()]){
            int height= heights[stack.pop()];
            int width;
            if(stack.isEmpty())
            width=i;
            else width=i-stack.peek()-1;
        ans=Math.max(ans,height*width);
       }
       stack.push(i);
        }
       return ans;
    }
}