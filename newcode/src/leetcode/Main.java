package leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    //求给定二叉树的最小深度。最小深度是指树的根结点到最近叶子结点的最短路径上结点的数量。
    //Given a binary tree, find its minimum depth.
    // The minimum depth is the number of nodes along the shortest path
    // from the root node down to the nearest leaf node.
    /*
        非递归层次遍历，找到第一个左右子树为null的节点，返回
     */
    public int run(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 0;
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()){
            int len = list.size();//获取该层节点数量
            depth ++;
            for (int i = 0; i < len; i++){
                TreeNode temp = list.remove(0);
                if (temp.left == null && temp.right == null)
                    return depth;
                if (temp.left != null)
                    list.add(temp.left);
                if (temp.right != null)
                    list.add(temp.right);
            }
        }
        return depth;

    }



    /*
    计算逆波兰式（后缀表达式）的值
    运算符仅包含"+","-","*"和"/"，被操作数可能是整数或其他表达式
    例如：
    ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9↵  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     */
    //操作数入栈，遇到运算符则弹出栈内2个元素运算，再把结果放入栈内
    public int evalRPN(String[] tokens) {
        if (tokens == null)
            return 0;
        Stack<Integer> stack1 = new Stack<>();

        for (int i = 0; i < tokens.length; i++){
            //遇到操作数则开始运算
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("/") || tokens[i].equals("*")){
                Integer pop1 = stack1.pop();
                Integer pop2 = stack1.pop();
                int temp = 0;
                switch (tokens[i]){
                    case("/"):
                        temp = pop2 / pop1;
                        break;
                    case("*"):
                        temp = pop2 * pop1;
                        break;
                    case("+"):
                        temp = pop2 + pop1;
                        break;
                    case("-"):
                        temp = pop2 - pop1;
                        break;
                }
                stack1.push(temp);
            }
            else//否则把操作数入栈
                stack1.push(Integer.parseInt(tokens[i]));
        }

        return stack1.pop();
    }

    //对于给定的n个位于同一二维平面上的点，求最多能有多少个点位于同一直线上
    //穷举


    public int maxPoints(Point[] points) {
        if(points == null){
            return 0;
        }
        if(points.length <= 2){
            return points.length;
        }
        Map<Double, Integer> map = new HashMap();
        int result = 0;
        for(int i=0;i<points.length;i++){
            map.clear();
            int overlap = 0;
            int vertical = 0;
            int horizon = 0;
            int max = 0;
            double rate = 0.0;
            for(int j=i+1;j<points.length;j++){
                double gapx = new Double(points[i].x) - new Double(points[j].x);
                double gapy = new Double(points[i].y) - new Double(points[j].y);
                if(gapx == 0 && gapy == 0){
                    overlap++;
                    continue;
                }else if(gapx == 0){
                    vertical++;
                    max = Math.max(max,vertical);
                }else if(gapy == 0){
                    horizon++;
                    max = Math.max(max,horizon);
                }else{
                    rate = gapy/gapx;
                    if(map.containsKey(rate)){
                        map.put(rate,map.get(rate)+1);
                    }else{
                        map.put(rate,1);
                    }
                    max = Math.max(max,map.get(rate));
                }
            }
            result=Math.max(result, max+overlap+1);
        }
        return result;
    }

    //在O(n log n)的时间内使用常数级空间复杂度对链表进行排序。
    /*
        归并排序。
     */
    public ListNode sortList(ListNode head) {
        if (head == null)
            return null;
        ListNode p = new ListNode(0);
        p.next = head;
        int len = 0;
        //得到链表的长度
        while (p.next != null){
            len ++;
            p = p.next;
        }
        ListNode bf = new ListNode(0);
        bf.next = head;
        //进行归并排序,循环切割，排序
        for (int size = 1; size < len; size <<= 1){
            ListNode cur = bf.next;
            ListNode tail = bf;
            while (cur != null){
                ListNode left = cur;
                ListNode right = cut(cur, size);//的到右半部分头结点
                cur = cut(right, size);//得到剩余部分头结点
                tail.next = merge(left, right);//排序
                while (tail.next != null)
                    tail = tail.next;//使tail处于最后一个位置
            }
        }
        return bf.next;
    }
    //截取size长度的链表,返回后半部分的头结点
    public ListNode cut(ListNode head, int size){
        ListNode p = head;
        while (--size > 0 && p != null)
            p = p.next;
        if (p == null)
            return null;
        ListNode next = p.next;
        p.next = null;
        return next;
    }
    //对两个链表排序，返回排序后头结点
    public ListNode merge(ListNode left, ListNode right){
        ListNode p = new ListNode(0);
        ListNode res = p;
        while (left != null && right != null){
            if (left.val < right.val){
                p.next = left;
                left = left.next;
            }else {
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        if (left != null)
            p.next = left;
        if (right != null){
            p.next = right;
        }
        return res.next;
    }


    //使用插入排序对链表进行排序。
    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return null;
        ListNode p = new ListNode(0);
        //截取头结点
        p.next = new ListNode(head.val);
        ListNode ln = head.next;
        ListNode res = p;
        int i = 1;//已排序节点数
        while (ln != null){
            int j = i;
            p = res;
            ListNode hn = new ListNode(ln.val);//当前要排序的节点，不能用hn = ln;hn.next = null;涉及引用传递
            while (j-- > 0){//在已排序链表中寻找插入位置
                if (p.next.val > hn.val){//找到插入位置
                    ListNode temp = p.next;
                    p.next = hn;
                    hn.next = temp;
                    break;
                }
                p = p.next;
            }
            if (p.next == null) {//在已排序链表中没有找到合适位置，则把节点添加到末尾
                p.next = hn;
            }
            //节点后移
            ln = ln.next;
            i++;//已排序节点加1
        }
        return res.next;
    }
    public static void main(String[] args){

        Main mn = new Main();

        /*TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(1);
        TreeNode tn3 = new TreeNode(1);
        TreeNode tn4 = new TreeNode(1);
        TreeNode tn5 = new TreeNode(1);
        tn1.left = tn2;
        tn2.left = tn3;
        tn2.right = tn4;
        tn1.right = tn5;
        System.out.println(mn.run(tn1));*/

        /*String[] str = {"4", "13", "5", "/", "+"};
        System.out.println(mn.evalRPN(str));*/

        /*Point point1 = new Point(0,0);
        Point point2 = new Point(-1,-1);
        Point point3 = new Point(2,2);

        Point[] points = {point1, point2, point3};
        System.out.println(mn.maxPoints(points));*/

        /*ListNode ln1 = new ListNode(4);
        ListNode ln2 = new ListNode(7);
        ListNode ln3 = new ListNode(5);
        ListNode ln4 = new ListNode(2);
        ListNode ln5 = new ListNode(8);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ListNode list = mn.sortList(ln1);
        while (list != null){
            System.out.println(list.val);
            list = list.next;
        }*/

        ListNode ln1 = new ListNode(4);
        ListNode ln2 = new ListNode(7);
        ListNode ln3 = new ListNode(5);
        ListNode ln4 = new ListNode(2);
        ListNode ln5 = new ListNode(8);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ListNode list = mn.insertionSortList(ln1);
        while (list != null){
            System.out.println(list.val);
            list = list.next;
        }
    }
}
