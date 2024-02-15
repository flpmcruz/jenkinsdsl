job('example-n2') {
  description('Descripcion de ejemplo')
  scm{
  	git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
            node / gitConfigName('Felipe')
            node / gitConfigEmail('me@me.com')
    }
  }
  parameters {
    	stringParam('nombre', defaultValue = 'Felipe', description = 'Parametro de cadena')
    	choiceParam('planeta', ['Merucrio', 'Pluton', 'Tierra'])
    	booleanParam('agente', false)
  }
  triggers {
        cron('H/7 * * * *')
  }
  steps {
  		shell("bash jobscript.sh")
  }
  publishers {
        mailer('flpmireles@gmail.com', true, true)
        slackNotifier {
              notifyAborted(true)
              notifyEveryFailure(true)
              notifyNotBuilt(false)
              notifyUnstable(false)
              notifyBackToNormal(true)
              notifySuccess(false)
              notifyRepeatedFailure(false)
              startNotification(false)
              includeTestSummary(false)
              includeCustomMessage(false)
              customMessage(null)
              sendAs(null)
              commitInfoChoice('NONE')
              teamDomain(null)
              authToken(null)
        }
  }
}