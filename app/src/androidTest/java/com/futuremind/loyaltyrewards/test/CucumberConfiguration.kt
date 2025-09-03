package com.futuremind.loyaltyrewards.test

import io.cucumber.junit.CucumberOptions

@CucumberOptions(
    glue = [ "com.futuremind.loyaltyrewards.steps" ],
    features = [ "features" ]
)
class CucumberConfiguration {
}