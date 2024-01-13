package dev.parfenov.leectode.medium;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Stack;

// https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/?envType=daily-question&envId=2024-01-10

/**
 * Идея решения:<br>
 * Построить новую структуру (суффиксное дерево) с ссылкой на родителя<br>
 * С помощью DFS найти ссылку на стартовую позицию<br>
 * И через BFS найти максимальную глубину относительно стартовой позиции
 * */
public class Problem2385 {
    static class Solution {
        public int amountOfTime(TreeNode root, int start) {
            var suffixRoot = new SuffixNode();

            //строим суффиксное дерево
            buildSuffixLinks(root, suffixRoot);

            //ищем стартовую позицию
            var suffix = dfsFindStartPosition(start, suffixRoot);

            //ищем максимальную глубину от этого узла
            return bfsMaxDepth(suffix);
        }

        void buildSuffixLinks(TreeNode root, SuffixNode suffixRoot) {
            var suffixQueue = new ArrayDeque<SuffixNode>();
            suffixQueue.add(suffixRoot);

            var nodeQueue = new ArrayDeque<TreeNode>();
            nodeQueue.add(root);

            while (!suffixQueue.isEmpty()) {
                var suffix = suffixQueue.pop();
                var node = nodeQueue.pop();

                suffix.val = node.val;

                if (node.left != null) {
                    suffix.left = new SuffixNode(suffix, node.left.val);
                    suffixQueue.add(suffix.left);
                    nodeQueue.add(node.left);
                }

                if (node.right != null) {
                    suffix.right = new SuffixNode(suffix, node.right.val);
                    suffixQueue.add(suffix.right);
                    nodeQueue.add(node.right);
                }
            }
        }

        SuffixNode dfsFindStartPosition(int start, SuffixNode suffixRoot) {
            var queue = new Stack<SuffixNode>();
            queue.push(suffixRoot);

            while (!queue.isEmpty()) {
                var node = queue.pop();
                if (node.val == start)
                    return node;

                if (node.left != null)
                    queue.push(node.left);

                if (node.right != null)
                    queue.push(node.right);
            }

            return suffixRoot;
        }

        int bfsMaxDepth(SuffixNode startPosition) {
            var maxDepth = 0;

            var suffixStack = new Stack<SuffixNode>();
            suffixStack.push(startPosition);

            var depthStack = new Stack<Integer>();
            depthStack.push(0); //начальная глубина для стартовой позиции == 0

            var visitedSuffix = new HashSet<SuffixNode>(); //посещенные узлы

            while (!suffixStack.isEmpty()) {
                var suffix = suffixStack.pop();
                var depth = depthStack.pop();
                maxDepth = Math.max(maxDepth, depth);

                visitedSuffix.add(suffix);

                if (suffix.parent != null && !visitedSuffix.contains(suffix.parent)) {
                    suffixStack.push(suffix.parent);
                    depthStack.push(depth + 1);
                }

                if (suffix.left != null && !visitedSuffix.contains(suffix.left)) {
                    suffixStack.push(suffix.left);
                    depthStack.push(depth + 1);
                }

                if (suffix.right != null && !visitedSuffix.contains(suffix.right)) {
                    suffixStack.push(suffix.right);
                    depthStack.push(depth + 1);
                }
            }

            return maxDepth;
        }

        static class SuffixNode {
            public int val;
            public SuffixNode parent;
            public SuffixNode left;
            public SuffixNode right;

            public SuffixNode() {
            }

            public SuffixNode(SuffixNode parent, int val) {
                this.parent = parent;
                this.val = val;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                SuffixNode that = (SuffixNode) o;
                return val == that.val;
            }

            @Override
            public int hashCode() {
                return Objects.hash(val);
            }

        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

        var v9 = new TreeNode(9);
        var v2 = new TreeNode(2);
        var v4 = new TreeNode(4, v9, v2);
        var v5 = new TreeNode(5, null, v4);
        var v10 = new TreeNode(10);
        var v6 = new TreeNode(6);
        var v3 = new TreeNode(3, v10, v6);
        var v1 = new TreeNode(1, v5, v3);

        var solution = new Solution();
        System.out.println(solution.amountOfTime(v1, 3)); // => 4
    }
}
