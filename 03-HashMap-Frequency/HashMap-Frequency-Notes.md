# HashMap / Frequency — Quick Notes

## When to reach for it (recognition)
- The inner loop is really a **search / lookup** ("have I seen X?", "how many X?").
- You're **counting**, **de-duplicating**, **grouping**, or hunting **complements**.
- You want to replace an O(n) inner scan with an O(1) average lookup.

## Two core uses
| Use | Question | Examples |
|-----|----------|----------|
| **Set** | "seen it before?" | dedup, complements, cycle checks |
| **Counter** | "how many of each?" | anagrams, top-K, majority |

## The whole craft = choosing the KEY
| Problem | Key |
|---------|-----|
| Two Sum | the value (look up complement) |
| Group Anagrams | sorted letters / 26-count signature |
| Subarray Sum = K | **running prefix sum** |
| Longest Consecutive | value in a HashSet |

## Complexity
- `get/put`: **O(1) average**, O(n) worst case (adversarial collisions; Java treeifies buckets).
- Two Sum: O(n) time, O(n) space.
- Group Anagrams: O(n·L log L) sorted key, or O(n·L) with count signature.
- **Trade:** buy O(1) lookups with O(n) memory.

## Bridge: Subarray Sum = K
`subarray sum = prefix[j] - prefix[i] = k` ⇒ at each `j`, count earlier prefixes equal to `prefix[j] - k`. Map = prefix-sum → count. Seed `{0:1}`.

## FAANG variants to practice
- **Two Sum** — Amazon, Google, Microsoft
- **Group Anagrams** — Amazon, Meta
- **Subarray Sum Equals K** — Meta, Google
- **Top K Frequent Elements** — Amazon, Microsoft
- **Valid Anagram / First Unique Character** — Amazon, Microsoft
- **Longest Consecutive Sequence** — Google, Meta

## Recognition drill
Inner loop = lookup? → hash map. Then: **name the key**, name the value stored. Quote O(1) *average* + O(n) space, acknowledge worst case.
