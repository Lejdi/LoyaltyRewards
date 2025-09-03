package com.futuremind.loyalityrewards.test

import io.cucumber.junit.CucumberOptions

@CucumberOptions(
    glue = [ "com.futuremind.loyalityrewards.steps" ],
    features = [ "features" ]
)
class CucumberConfiguration {
}