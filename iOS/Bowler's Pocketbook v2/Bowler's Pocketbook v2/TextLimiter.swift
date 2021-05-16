//
//  TextLimiter.swift
//  Bowler's Pocketbook v2
//
//  Created by Jeffrey Preston on 5/16/21.
//

import SwiftUI

class TextLimiter: ObservableObject {
    private let limit: Int

    init(limit: Int) {
        self.limit = limit
    }

    @Published var value = "" {
        didSet {
            if value.count > self.limit {
                value = String(value.prefix(self.limit))
                self.hasReachedLimit = true
            } else {
                self.hasReachedLimit = false
            }
        }
    }
    @Published var hasReachedLimit = false
}
