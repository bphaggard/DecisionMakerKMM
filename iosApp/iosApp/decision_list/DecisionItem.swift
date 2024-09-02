//
//  DecisionItem.swift
//  iosApp
//
//  Created by Patrik Mlcoch on 28.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DecisionItem: View {
    var decision: Decision
    var onDeleteClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(decision.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onDeleteClick) {
                    Image(systemName: "trash").foregroundColor(.red)
                }
            }
        }
    }
}

struct DecisionItem_Previews: PreviewProvider {
    static var previews: some View {
        DecisionItem(
            decision: Decision(id: nil, title: "Kolo"),
            onDeleteClick: {}
        )
    }
}
