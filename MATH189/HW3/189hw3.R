data <- read.csv("hcmv.txt")

n = 296
N = 229354

#q1
hist(data$location, breaks=58, xlim=c(0, 240000), ylim=c(0, 20),
     main = 'Frequency of Palindromes in CMV DNA',
     xlab='Location on Chain of Base Pairs')

#three simulations of 296 from 229354
ran1 <- runif(n, 0, N)
ran2 <- runif(n, 0, N)
ran3 <- runif(n, 0, N)

#sample from our simulations
hist(ran1, breaks=58, xlim=c(0, 240000), ylim=c(0, 20),
     main = 'First Simulation of Uniformly Random Palindromes in CMV DNA',
     xlab='Location on Chain of Base Pairs')
hist(ran2, breaks=58, xlim=c(0, 240000), ylim=c(0, 20),
     main = 'Second Simulation of Uniformly Random Palindromes in CMV DNA',
     xlab='Location on Chain of Base Pairs')
hist(ran3, breaks=58, xlim=c(0, 240000), ylim=c(0, 20),
     main = 'Third Simulation of Uniformly Random Palindromes in CMV DNA',
     xlab='Location on Chain of Base Pairs')

#q2

#consecutive palindromes
spacings <- diff(data$location, lag=1)
lambda <- 1 / mean(spacings)
labl = paste("Distance between", "Consecutive","Palindromes")

hist(spacings, col=rgb(1,0,0,0.5), 
     main="Palindrome Spacings Distribution Comparison", 
     breaks=seq(0, 8000, 500),
     xlab=labl)
hist(rgamma(n, shape=1, rate=lambda), add=T, breaks=seq(0, 10000, 500),
     col=rgb(0,1,0,0.5))
legend('topright', legend=c('Observed','Exponential / Gamma'), 
       lwd = c(4, 4), col=c(rgb(1,0,0,0.5), rgb(0,1,0,0.5)))

#sum of pairs of palindromes
spacings <- diff(data$location, lag=2)
lambda <- 1 / mean(spacings)
labl = paste("Distance between", "Paired","Palindromes")

hist(spacings, col=rgb(1,0,0,0.5), 
     main="Palindrome Spacings Distribution Comparison", 
     breaks=seq(0, 15000, 1000),
     xlab=labl)
hist(rgamma(n, shape=2, rate=lambda), add=T, breaks=seq(0, 30000, 1000),
     col=rgb(0,1,0,0.5))
legend('topright', legend=c('Observed','Exponential / Gamma'), 
       lwd = c(4, 4), col=c(rgb(1,0,0,0.5), rgb(0,1,0,0.5)))

#sum of triplets of palindromes
spacings <- diff(data$location, lag=3)
lambda <- 1 / mean(spacings)
labl = paste("Distance between", "Triplet","Palindromes")

hist(spacings, col=rgb(1,0,0,0.5), 
     main="Palindrome Spacings Distribution Comparison", 
     breaks=seq(0, 25000, 1000),
     xlab=labl)
hist(rgamma(n, shape=3, rate=lambda), add=T, breaks=seq(0, 40000, 1000),
     col=rgb(0,1,0,0.5))
legend('topright', legend=c('Observed','Exponential / Gamma'),
       lwd = c(4, 4), col=c(rgb(1,0,0,0.5), rgb(0,1,0,0.5)))

#bin size 55, 58, 60 for residuals and histograms of locations
for (br in c(4200, 4000, 3800)) {
  labl1 = paste('Frequency of Palindromes in CMV DNA', 
                "( Regions of Length", br, 
                ")")
  labl2 = paste('Standardized Residuals', "( Regions of Length", br, 
                ")")
  
  obs <- hist(data$location, breaks=seq(0, 240000, br), xlim=c(0, 240000), 
              ylim=c(0, 20),
       main = labl1,
       xlab='Location on Chain of Base Pairs', col=rgb(1,0,0,0.5))
  unif <- hist(ran1, breaks=seq(0, 240000, br), xlim=c(0, 240000), 
               ylim=c(0, 20), xlab='Location on Chain of Base Pairs', 
               col=rgb(0,1,0,0.5), add=T)
  legend('topright', legend=c('Observed','Uniform'),
         lwd = c(4, 4), col=c(rgb(1,0,0,0.5), rgb(0,1,0,0.5)))
  residual <- (obs$counts - unif$counts) / sqrt(unif$counts)
  plot(residual, type='h', main = labl2, ylab="Standard Residual")
  abline(h=3, col=rgb(1,0,0,.5))
}

#q4

#init arrays where array index is the number of breaks k
p_val <- array(dim = c(100, 1))
interval_length <- array(dim = c(100, 1))
start_interval <- array(dim = c(100, 1))
end_interval <- array(dim = c(100, 1))
lambda_hat <- array(dim = c(100, 1))
max_count <- array(dim = c(100, 1))

for (k in 20 : 90){
  tab <- table(cut(data$location, breaks=seq(0, N, length.out = k + 1), 
                   include.lowest=TRUE))
  
  tab <- as.vector(tab)
  lambda_hat[k,] <-sum(tab) / k
  max_count[k,] <- max(tab)
  tmp <- 0
  interval_length[k,] <- N / k
  start_interval[k,] <- (N / k) * which(tab == max(tab))[1] - (N / k)
  end_interval[k,] <- (N / k) * which(tab == max(tab))[1]
  
  for (i in 0:(max(tab) - 1)){
    tmp <- tmp + ( (lambda_hat[k] ** i) * exp( -lambda_hat[k] ) / 
                         factorial(i) )
  }
  
  p_val[k,] <- 1 - tmp ** k
  
}

table <- data.frame(lambda_hat,interval_length, p_val, max_count, 
                    start_interval, end_interval)

