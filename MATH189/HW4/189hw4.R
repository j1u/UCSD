#read data in
data <- read.csv("gauge.txt")
den <- data$density
gain <- data$gain
den_log_gain = data.frame(den, log(gain))

#q1

#plot points
plot(gain ~ den, xlab="Density (g/cm^3)", ylab="Gain", 
     main="Density vs. Gain With Regression Line")
#linear model
l = lm(gain ~ den)
abline(l, col=rgb(1,0,0,.75), lwd=2)
legend("topright", legend=c("Regression Line"),
       col=c(rgb(1,0,0,.75)), lwd=2)

#plot residuals
plot(residuals(l) / sd(residuals(l)) ~ den, 
     xlab="Density (g/cm^3)", ylab="Standardized Residuals",
     main="Residuals of Density vs. Gain")
abline(h=0, lwd=2)

#q2
#plot data
plot(log(gain) ~ den, xlab="Density (g/cm^3)", ylab="log(Gain)",
     main="Density vs. log(Gain) With Regression Line")
#linear model
l = lm(log(gain) ~ den)
abline(l, col=rgb(1,0,0,.75), lwd=2)
legend("topright", legend=c("Regression Line"),
       col=c(rgb(1,0,0,.75)), lwd=2)

#plot residuals
plot(residuals(l) / sd(residuals(l)) ~ den, xlab="Density (g/cm^3)", 
     ylab="Standardized Residuals",
     main="Residuals of Density vs. log(Gain)")
abline(h=0, lwd=2)

#plot qqnorm
qqnorm(residuals(l) / sd(residuals(l)), xlim=c(-3,3), ylim=c(-3,3))
abline(c(0,1), col = rgb(1,0,0,.75))
legend("topleft", legend=c("Standard Normal Line"),
       col=c(rgb(1,0,0,.75)), lwd=2)

#q3
ten_percent_error = data$density
fifteen_percent_error = data$density
twenty_percent_error = data$density

#populate new densities
for (i in 1:length(data$density)) {
  ten_err = runif(1, -ten_percent_error[i] * .1, 
                  ten_percent_error[i] * .1)
  fif_err = runif(1, -fifteen_percent_error[i] * .15, 
                  fifteen_percent_error[i] * .15)
  tw_err = runif(1, -twenty_percent_error[i] * .2, 
                 twenty_percent_error[i] * .2)
  
  ten_percent_error[i] = ten_percent_error[i] + ten_err
  fifteen_percent_error[i] = fifteen_percent_error[i] + fif_err
  twenty_percent_error[i] = twenty_percent_error[i] + tw_err
}

#original r ** 2
original_rsq = summary(lm(l))$r.squared

#plot all new densities against gain with regression line
par(mar=c(5,5,5,5))
plot(log(gain) ~ ten_percent_error, xlab="Density (g/cm^3)", ylab="log(Gain)",
     main="10% Error Density vs. log(Gain) With Regression Line",
     xlim=c(0,.8))
abline(l, col=rgb(1,0,0,.75), lwd=2)
legend("topright", legend=c("Regression Line"),
       col=c(rgb(1,0,0,.75)), lwd=2)
ten_per_rsq = summary(lm(log(gain) ~ den, data=data.frame(den=ten_percent_error, 
                                                          gain)))$r.squared
# plot(residuals(l) / sd(residuals(l)) ~ ten_percent_error, xlab="Density", 
#      ylab="Standardized Residuals",
#      main="Residuals of 10% Error Density vs. log(Gain)")
# abline(h=0, lwd=2)
plot(log(gain) ~ fifteen_percent_error, xlab="Density (g/cm^3)", ylab="log(Gain)",
     main="15% Error Density vs. log(Gain) With Regression Line",
     xlim=c(0,.8))
abline(l, col=rgb(1,0,0,.75), lwd=2)
legend("topright", legend=c("Regression Line"),
       col=c(rgb(1,0,0,.75)), lwd=2)
fif_per_rsq = summary(lm(l, data=data.frame(den=fifteen_percent_error, 
                                            gain)))$r.squared
# plot(residuals(l) / sd(residuals(l)) ~ fifteen_percent_error, xlab="Density", 
#      ylab="Standardized Residuals",
#      main="Residuals of 15% Error Density vs. log(Gain)")
# abline(h=0, lwd=2)
plot(log(gain) ~ twenty_percent_error, xlab="Density (g/cm^3)", ylab="log(Gain)",
     main="20% Error Density vs. log(Gain) With Regression Line",
     xlim=c(0,.8))
abline(l, col=rgb(1,0,0,.75), lwd=2)
legend("topright", legend=c("Regression Line"),
       col=c(rgb(1,0,0,.75)), lwd=2)
twenty_per_rsq = summary(lm(l, data=data.frame(den=twenty_percent_error, 
                                               gain)))$r.squared
# plot(residuals(l) / sd(residuals(l)) ~ twenty_percent_error, xlab="Density", 
#      ylab="Standardized Residuals",
#      main="Residuals of 20% Error Density vs. log(Gain)")
# abline(h=0, lwd=2)

#q4
plot(log(gain) ~ den, xlab="Density (g/cm^3)", ylab="log(Gain)",
     main="Density vs. log(Gain) With Regression Line",)
abline(l, col=rgb(1,0,0,.75), lwd=2)
new_den = seq(min(den), max(den), length.out=length(den))

#prediction / confidence lines
prediction_lines = predict(l, newdata=data.frame(den=new_den), 
                           interval="prediction")
confidence_lines = predict(l, newdata=data.frame(den=new_den), 
                           interval="confidence")
#lower bound
lines(prediction_lines[,2] ~ new_den, lwd=1, lty=2, col=rgb(0,0,1,1))
lines(confidence_lines[,2] ~ new_den, lwd=1, lty=2, col=rgb(0,1,0,1))
#upper bound
lines(prediction_lines[,3] ~ new_den, lwd=1, lty=2, col=rgb(0,0,1,1))
lines(confidence_lines[,3] ~ new_den, lwd=1, lty=2, col=rgb(0,1,0,1))
legend("topright", legend=c("Regression Line", "95% Prediction Bands", 
                            "95% Confidence Bands"),
       col=c(rgb(1,0,0,1), rgb(0,0,1,1), rgb(0,1,0,1)), lty=c(1,2,2), 
       lwd=c(2,1,1))

lower_coeff = lm(prediction_lines[,2] ~ new_den)$coefficients
upper_coeff = lm(prediction_lines[,3] ~ new_den)$coefficients

#predicted gains
first_pred_pt = l$coefficients[1] + (l$coefficients[2] * .508)
first_pred_range = c(lower_coeff[1] + (lower_coeff[2] * .508), 
                     upper_coeff[1] + (upper_coeff[2] * .508))

second_pred_pt = l$coefficients[1] + (l$coefficients[2] * .001)
second_pred_range = c(lower_coeff[1] + (lower_coeff[2] * .001), 
                     upper_coeff[1] + (upper_coeff[2] * .001))

#observed gains
first_observed_gain_pt = median(den_log_gain[den_log_gain$den == .508, 2])
first_observed_gain_range = range(den_log_gain[den_log_gain$den == .508, 2])
second_observed_gain_pt = median(den_log_gain[den_log_gain$den == .001, 2])
second_observed_gain_range = range(den_log_gain[den_log_gain$den == .001, 2])

#mean squared errors
mse_001 = sum(l$residuals[81:90] ** 2) / 10
mse_508 = sum(l$residuals[21:30] ** 2) / 10
