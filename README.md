
# CMU_Coin-flipping_Experience

A Coin Flipping machine inspired by the IBM Q Experience.

15-459 Assignment

## Usage

java -jar xxx.jar \<input file> \<operator> \<coin count (default 5)>


## Available Operators

Tester: Run a simulation and print results

Prob0Init: Print the probabilities of each possible outcome (0 initialized)

ProbAllInit: Print the probabilities of each coin being 1 for all initializations


## Input Commands:

```
$$ \begin{align*}  
    \text{Flip} & & i & & \text{(randomly set coin~$i$ to $0$ or $1$ with probability $1/2$ each)} \\  
    \text{Not} & & i & & \text{(turn over the $i$th coin; i.e., deterministically reverse its $0$/$1$ status)} \\  
    \text{CNot} & & i\ j & & \text{(if coin~$i$ is $1$ (Tails) then do a Not on coin~$j$, else do nothing)}\\  
    \text{CSwap} & & i\ j\ k & & \text{(if coin~$i$ is $1$ (Tails) then swap the values of coins $j$ and $k$)}  
\end{align*}
\begin{align*}  
    \text{CCNot} & & i\ j \ k& & \text{(if coins~$i$ and $j$ are \emph{both} $1$ then do `Not~$k$', else do nothing)}\\  
    \text{GenFlip} & & i\ p & & \text{(set coin~$i$ to $0$ with probability $1-p$, to $1$ with probability $p$)} \\  
    \text{Gen1Bit} & & i\ p\ q & & \text{(if coin~$i$ is $0$ then make it~$1$ with probability $p$,\qquad}\\  
      & &  & & \text{else if coin~$i$ is $1$ then make it~$0$ with probability $q$)}  
\end{align*} $$
```