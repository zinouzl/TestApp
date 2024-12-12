//
//  ContentView.swift
//  iosApp
//
//  Created by Zine Eddine LATIOUI on 26/10/2024.
//

import SwiftUI
import UIKit
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}
