//
//  KeyboardObserver.swift
//  iosApp
//
//  Created by Patrik Mlcoch on 04.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Combine

class KeyboardObserver: ObservableObject {
    @Published var keyboardHeight: CGFloat = 0
    
    private var cancellable: AnyCancellable?
    
    init() {
        self.cancellable = NotificationCenter.default
            .publisher(for: UIResponder.keyboardWillChangeFrameNotification)
            .merge(with: NotificationCenter.default.publisher(for: UIResponder.keyboardWillHideNotification))
            .sink { [weak self] notification in
                guard let self = self else { return }
                if notification.name == UIResponder.keyboardWillHideNotification {
                    self.keyboardHeight = 0
                } else {
                    self.keyboardHeight = self.getKeyboardHeight(from: notification)
                }
            }
    }
    
    deinit {
        cancellable?.cancel()
    }
    
    private func getKeyboardHeight(from notification: Notification) -> CGFloat {
        guard let keyboardFrame = notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? CGRect else {
            return 0
        }
        return keyboardFrame.height
    }
}
