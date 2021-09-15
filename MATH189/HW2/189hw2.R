#library to calculate kurt and skewness
library(moments)

#import data into r
data <- read.csv("videodata.txt", header = TRUE)
data2 <- read.csv("videoMultiple.txt", header = TRUE)

#dataframes for students who did and did not play video games
#the week prior to the survey
played_games <- data[data$time > 0 & data$time < 99,]
didnt_play <- data[data$time == 0,]

#sample size n
n = dim(played_games)[1] + dim(didnt_play)[1]
#correction factor
correction_factor = (314 - n) / (314 - 1)

#q1

#find prop of students who played games
prop = dim(played_games)[1] / n

#margin of error for CI
me = qnorm(.975) * sqrt((prop * (1-prop)) / n)
#population corrected margin of error
pop_corr_me = qnorm(.975) * sqrt((prop * (1-prop)) / (n-1)) * 
  sqrt(correction_factor)

#calculate ci's
ci = c(prop - me, prop + me)
pop_corr_ci = c(prop - pop_corr_me, prop + pop_corr_me)

#number of simulations for bootstrapping
num_sims = 1000

#init dataframe for the simulation distribution
sim_dist <- c(length(num_sims))
#init population for simulation
pop <- rep(data$time, length.out = 314)

#run boostrapping procedure
for (i in 1:num_sims){
  sim = sample(pop, n, replace = FALSE)
  sim_prop = length(sim[sim > 0]) / n
  
  sim_dist[i] = sim_prop
}

#generate histogram for the prop of students who played video games the week 
#prior to the survey
hist(sim_dist, breaks = 15, 
     xlab = "Proportion of Students Who Played a Video Game During Exam Week", 
     main = "Distribution of 1000 Bootstrapped Proportions")
abline(v = mean(sim_dist), col = 'red', lwd = 2)

#calculate the mean and CI's of the new bootstrap sample
bs_mean_q1 = mean(sim_dist)
bs_me_q1 = qnorm(.975) * sqrt((bs_mean_q1 * (1-bs_mean_q1)) / n)
bs_pop_corr_me_q1 = qnorm(.975) * sqrt((bs_mean_q1 * (1-bs_mean_q1)) / (n-1)) * 
  sqrt(correction_factor)
bs_ci_q1 = c(bs_mean_q1 - bs_me_q1, bs_mean_q1 + bs_me_q1)
bs_ci_corr_q1 = c(bs_mean_q1 - bs_pop_corr_me_q1, bs_mean_q1 + bs_pop_corr_me_q1)

#q2
#clean time variable from 99s
cleaned_time = data[data$time >= 0 & data$time < 99 & data$freq < 99,]
#adjust plot dimensions
par(mar=c(5, 6, 4, 4))
#generate boxplot based on # hrs played and freq of play
boxplot(cleaned_time$time~cleaned_time$freq, data, 
        xlab = "Frequency of Play", ylab = "Number of Hours Played the 
        Week Before Survey",
        main = "Time Playing Games Based on Frequency",
        names = c("Daily", "Weekly", "Monthly", "Semesterly"))
#find the avg hrs played and prop of players who play when busy 
#for each category in freq of play
for (i in 1:4){
  data_freq = data[data$freq == i,]
  no_play_if_busy_prop = dim(data_freq[data_freq$busy == 0,])[1] / dim(data_freq)[1]
  play_if_busy_prop = dim(data_freq[data_freq$busy == 1,])[1] / dim(data_freq)[1]
  
  avg_hrs <- mean(data_freq$time)
  
  print(c(i, avg_hrs))
  print(c(i, no_play_if_busy_prop, play_if_busy_prop))
}

#q3
#find average time played
avg_time_played = mean(data$time)

#create histogram for time played
hist(data$time, breaks = 25, main = "Frequency of Time Played", 
     xlab = "Time Played the Week Before Survey (Hours)")

#computations for confidence intervals
time_played_me = qnorm(.975) * (sd(data$time) / sqrt(n))
time_played_corr = time_played_me * sqrt(correction_factor)
time_played_ci = c(avg_time_played - time_played_me, 
                   avg_time_played + time_played_me)
time_played_ci_corr = c(avg_time_played - time_played_corr, 
                   avg_time_played + time_played_corr)

#create dataframes for simulations
bs_means <- c(length(num_sims))
norm_kurt <- c(length(num_sims))
norm_skew <- c(length(num_sims))

#bootstrap for sample mean and normal skew / kurtosis
pop <- rep(data$time, length.out = 314)
for (i in 1:num_sims){
  sim = sample(pop, n, replace = FALSE)
 
  sim_mean = mean(sim)
  
  norm_kurt[i] = kurtosis(rnorm(n))
  norm_skew[i] = skewness(rnorm(n))
  bs_means[i] = sim_mean
}

#compute kurtosis / skew for bootstrap mean and normal distribution
bs_kurt = kurtosis(bs_means)
bs_skew = skewness(bs_means)
kurt_mean = mean(norm_kurt)
skew_mean = mean(norm_skew)

#create histograms for each distribution
hist(norm_kurt, main = "Distribution of 1000 Bootstrapped Time Played Kurtosis", 
     xlab = "Kurtosis")

hist(norm_skew, main = "Distribution of 1000 Bootstrapped Time Played Skewness", 
     xlab = "Skewness")

hist(bs_means, main = "Distribution of 1000 Bootstrapped Time Played Means", 
     xlab = "Time Played the Week Before Exam (Hours)")

#calculations for CI's for simulated means and kurtosis / skewness
bs_me = qnorm(.975) * (sd(bs_means) / sqrt(n))
bs_mean = mean(bs_means)
bs_me_corr = qnorm(.975) * (sd(bs_means) / sqrt(n)) * sqrt(correction_factor)
bs_ci = c(bs_mean - bs_me, bs_mean + bs_me)
bs_ci_corr = c(bs_mean - bs_me_corr, bs_mean + bs_me_corr)

#4
#compute proportion of sample that like games
like_games = data[data$like == 2 | data$like == 3,]
like_games_prop = dim(like_games)[1] / n

# sim_like_prop <- c(length(num_sims))
# 
# pop <- rep(data$like, length.out = 314)
# for (i in 1:num_sims){
#   sim = sample(pop, n, replace = FALSE)
#   sim_prop = length(sim[sim == 2 | sim == 3]) / n
#   
#   sim_like_prop[i] = sim_prop
# }

#sim_like_mean = mean(sim_like_prop)

#compute proportion of students who dislike games / didnt answer
dislike_games = data[data$like == 4 | data$like == 5,]
dislike_games_prop = dim(dislike_games)[1] / n

