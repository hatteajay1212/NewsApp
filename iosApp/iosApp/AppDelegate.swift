//
//  AppDelegate.swift
//  iosApp
//
//  Created by Admin on 05/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit
import BackgroundTasks
import shared

class AppDelegate : NSObject, UIApplicationDelegate{
    
    let appProcessingTaskId = "background.task"
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        BGTaskScheduler.shared.register(forTaskWithIdentifier: appProcessingTaskId, using: nil) { task in
            print("[BGTASK] Perform bg processing \(self.appProcessingTaskId)")
            
            print("message")
            let preferences = SharedPrefrences()
            preferences.setMobileNumber(mobileNumber: "7887593183")
            
            self.scheduleBackgroundProcessing()
            task.setTaskCompleted(success: true)
        }
        
        scheduleBackgroundProcessing()
        
        return true
    }
    
    func scheduleBackgroundProcessing() {
            let request = BGAppRefreshTaskRequest(identifier: appProcessingTaskId)
//            request.requiresNetworkConnectivity = true // Need to true if your task need to network process. Defaults to false.
//            request.requiresExternalPower = true // Need to true if your task requires a device connected to power source. Defaults to false.

            request.earliestBeginDate = Date(timeIntervalSinceNow: 1 * 60) // Process after 5 minutes.

            do {
                try BGTaskScheduler.shared.submit(request)
            } catch(let error) {
                print("Could not schedule background Processing: \(error.localizedDescription)")
        }
    }
}
