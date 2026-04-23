/*
2452. Words Within Two Edits of Dictionary
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English letters and have the same length.

In one edit you can take a word from queries, and change any letter in it to any other letter. Find all words from queries that, after a maximum of two edits, equal some word from dictionary.

Return a list of all words from queries, that match with some word from dictionary after a maximum of two edits. Return the words in the same order they appear in queries.

 

Example 1:

Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
Output: ["word","note","wood"]
Explanation:
- Changing the 'r' in "word" to 'o' allows it to equal the dictionary word "wood".
- Changing the 'n' to 'j' and the 't' to 'k' in "note" changes it to "joke".
- It would take more than 2 edits for "ants" to equal a dictionary word.
- "wood" can remain unchanged (0 edits) and match the corresponding dictionary word.
Thus, we return ["word","note","wood"].
Example 2:

Input: queries = ["yes"], dictionary = ["not"]
Output: []
Explanation:
Applying any two edits to "yes" cannot make it equal to "not". Thus, we return an empty array.
 

Constraints:

1 <= queries.length, dictionary.length <= 100
n == queries[i].length == dictionary[j].length
1 <= n <= 100
All queries[i] and dictionary[j] are composed of lowercase English letters. */

// Solution

class Solution {
    
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        
        TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    
    class Trie {
        private TrieNode root;
        
        public Trie(String[] words) {
            root = new TrieNode();
            for (String word : words) {
                insert(word);
            }
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }
        
        public TrieNode getRoot() {
            return root;
        }
    }
    
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        
        Trie trie = new Trie(dictionary);
        TrieNode root = trie.getRoot();
        
        for (String query : queries) {
            if (dfs(root, query, 0, 0)) {
                res.add(query);
            }
        }
        
        return res;
    }
    
    private boolean dfs(TrieNode node, String query, int pos, int editsUsed) {
        if (editsUsed > 2) {
            return false;
        }
        
        if (pos == query.length()) {
            return node.isEnd;
        }
        
        char currChar = query.charAt(pos);
        int index = currChar - 'a';
        
        if (node.children[index] != null) {
            if (dfs(node.children[index], query, pos + 1, editsUsed)) {
                return true;
            }
        }
       
        for (int i = 0; i < 26; i++) {
            if (i != index && node.children[i] != null) {
                if (dfs(node.children[i], query, pos + 1, editsUsed + 1)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}