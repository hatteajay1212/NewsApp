import SwiftUI
import Zip
import shared

struct ContentView: View {
    
    var viewModel = NewsHomeViewModel(service: NewsApiServiceImpl(fileHandler: FileHandler()))

	var body: some View {
        Text("Hello").onAppear(perform: {
            viewModel.fetchTopHeadlines()
        })
        
        
        Button("Send Logs", action: {
            zipLogFiles()
        })
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

func zipLogFiles(){
    let sharedPreferences = SharedPrefrences()
    let logFilePath = sharedPreferences.getCurrentLogFilePath()
    
    let fileManager = FileManager.default
    let documentDirectory = NSSearchPathForDirectoriesInDomains(.documentDirectory, .userDomainMask, true)[0]
    
    let zipFilePath = "\(documentDirectory)/Logs.zip"
    print("log file path -> \(zipFilePath)")
    
    do{
        try Zip.zipFiles(paths: [URL(fileURLWithPath: logFilePath)], zipFilePath: URL(fileURLWithPath: zipFilePath), password: nil) { (progress) in
        }
    }catch{
        
    }
}
