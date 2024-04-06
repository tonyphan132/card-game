public class Algo {
    public static void main(String[] args){
        int [] arr = {1,4,6,7,11,14,21,42,50,57};
        System.out.println(binarySearch(arr,7));
    }

    private static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int middle = (right + left)/2;
            if (arr[middle] == target){
                return middle;
            }
            else if (target < arr[middle]){
                right = middle - 1;
            }
            else{
                left = middle + 1;
            }
        }
        return -1;
    }
}
