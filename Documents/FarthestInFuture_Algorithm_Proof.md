
## Lemmas

#### (1) Let S be a reduced schedule that makes the same eviction decisions as S<sub>FF</sub> through the first j items in the sequence, for a number j. Then there is a reduced schedule S<sup>'</sup> that makes the same eviction decision as S<sub>FF</sub> through the first j + 1 items, and incurs no more misses than S does.
* Consider S = S<sub>FF</sub> for j first items and S' = S<sub>FF</sub> j+1 first items, 
we want to prove that misses<sub>S'</sub> <= misses<sub>S</sub>
* Consider the (j+1) request for an item `d`, we have the following scenarios
   1. Item `d` is already in the caches of both S and S', this satisfies the requirement for misses
   2. If `d` is not in the cache but both evict the same item, S and S' also ends up with the same cache
   so this also satisfies the requirements for misses   
   3. `d` is not in cache and S evicts `f` while S<sub>FF</sub> evicts an item`e != f`
   , then
        * At j + 1: S' = S<sub>FF</sub> != S
        * So now we have to ensure that S' incurs no more misses than S
        * We'll try to have S' get its cache back to the same state as S 
        as quickly as possible, while not incurring unnecessary misses, Once
        the caches are the same, we can finish the construction of S' by having it behave like S
        for the rest.
* Consider case (iii), from (j+2) onward, S' behaves exact like S until one of the following things
happen for the 1st time:
    1. A request is made for an item `g != e,f`, not in S, and S evicts `e` to
    make room for it. S' differs from S only on `e` and `f`, so `g` is not in S' 
    either; so we can have S' evict f, and now caches of S and S' are the same.
    We continue by constructing S' the same as S for the rest. This satisfies the
    requirements for the misses as the caches are now equal, and the 
    number of evictions so far are also equal.
    2. A request is made to `f` (which S evicted while S' kept), and S evicts `e'`, we consider the 2 following cases
        * If `e' = e`, then S' can access f from the cache directly, while
        S has to access it from main memory so now the misses<sub>S'</sub> 
        < misses<sub>S</sub> which satisfies the requirement
        * If `e' != e`, then we just make S' evicts e as well. However,
         if we do this S' s no longer a reduced schedule though now the 
         caches are the same. So to finish, we need to transform S' into
         its reduced form S'<sub>reduced</sub> (as proven in the book this
         does not increase the number of items brought in by S' and still agrees 
         with S<sub>FF</sub> through step j + 1)
* So in all cases we have a new reduced schedule S' that agrees with S<sub>FF</sub>
through j + 1 items and incurs no more misses than S does

* And crucially—here is where we use the defining property of the Farthest-in-
  Future Algorithm—one of these two cases will arise before there is a reference
  to e. This is because in step j + 1, Farthest-in-Future evicted the item (e) that
  would be needed farthest in the future; so before there could be a request to
  e, there would have to be a request to f , and then case (ii) above would apply.
        
**Q.E.D**




## Optimality proof: SFF incurs no more misses than any other schedule S* and hence is optimal.

Using this result, it is easy to complete the proof of optimality. We begin
with an optimal schedule S∗, and use the Lemma above to construct a schedule S<sub>1</sub> that
agrees with S<sub>FF</sub> through the first step. We continue applying the Lemma inductively
for j = 1, 2, 3, . . . ,m, producing schedules S<sub>j</sub> that agree with S<sub>FF</sub> through the
first j steps. Each schedule incurs no more misses than the previous one; and
by definition S<sub>m</sub> =  S<sub>FF</sub>, since it agrees with it through the whole sequence.


**Q.E.D**





