# Linked List — Quick Notes

## When to reach for these techniques
- Data is a **chain of nodes** joined by `next` — no random access.
- Operation is **re-linking**: reverse, remove, merge, reorder.
- **Cycle / middle / nth-from-end** → fast & slow pointers.

## The golden rule
**Always save `next` BEFORE overwriting a pointer** — otherwise you lose the tail.

## Reverse in place (3 pointers)
```
prev=null, curr=head
while curr: next=curr.next; curr.next=prev; prev=curr; curr=next
return prev   // new head
```

## Fast & slow (Floyd)
- slow +1, fast +2.
- **Cycle:** inside a loop the gap shrinks by 1 each step → they must collide.
- **Middle:** slow lands on the midpoint when fast reaches the end.
- **Cycle start:** after meeting, reset one pointer to head, advance both by 1.

## Dummy node
A placeholder before `head` → deleting the head, empty list, changing head all become ordinary iteration. Used in merge, remove-nth, insert.

## Complexity
| Op | Time | Space |
|----|------|-------|
| Reverse / merge / cycle | O(n) | O(1) |
| Recursive reverse | O(n) | O(n) call stack |

## Gotchas
- Null checks: `fast != null && fast.next != null`.
- Losing the tail (save-before-overwrite).
- Off-by-one on nth-from-end (use dummy, lead by n).
- Test: empty, single node, two nodes, cyclic.

## FAANG variants to practice
- **Reverse Linked List I & II** — Amazon, Microsoft, Meta
- **Linked List Cycle I & II** — Amazon, Google
- **Merge Two / K Sorted Lists** — Amazon, Meta
- **Remove Nth From End** — Meta, Microsoft
- **Reorder List / Palindrome List** — Amazon, Meta
- **Copy List with Random Pointer** — Amazon, Microsoft

## Recognition drill
Re-linking → pointer surgery + dummy. Cycle/middle/nth → fast & slow. Save-before-overwrite. Dry-run empty/single/two-node.
