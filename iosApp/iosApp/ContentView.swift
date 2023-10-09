import SwiftUI
import shared

struct ContentView: View {
    
    var viewModel = NewsHomeViewModel(service: NewsApiServiceImpl())

	var body: some View {
//        let preferences = SharedPrefrences()
//        let mobileNumber = preferences.getMobileNumber()
        Text("Hello").onAppear(perform: {
            viewModel.fetchTopHeadlines()
        })
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
